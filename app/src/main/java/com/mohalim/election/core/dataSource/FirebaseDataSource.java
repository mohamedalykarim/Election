package com.mohalim.election.core.dataSource;

import android.app.Application;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.mohalim.election.core.models.Elector;
import com.mohalim.election.core.models.UserItem;
import com.mohalim.election.core.utils.Constants;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableEmitter;
import io.reactivex.rxjava3.core.CompletableOnSubscribe;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableEmitter;
import io.reactivex.rxjava3.core.FlowableOnSubscribe;

public class FirebaseDataSource {
    private static final String TAG = "FirebaseDataSource";
    FirebaseFirestore database;

    public FirebaseDataSource(Application application) {
        database = FirebaseFirestore.getInstance();
    }

    public Completable login(String username, String password) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(@NonNull CompletableEmitter emitter) throws Throwable {
                database.collection("users")
                        .whereEqualTo("username",username)
                        .whereEqualTo("password", password)
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                if (queryDocumentSnapshots.isEmpty()){
                                    emitter.onError(new Throwable("رجاء التأكد من اسم المستخدم وكلمة المرور"));
                                }else {
                                    UserItem userItem = queryDocumentSnapshots.getDocuments().get(0).toObject(UserItem.class);
                                    Prefs.putString(Constants.NAME, userItem.getName());
                                    Prefs.putString(Constants.USER_NAME, userItem.getUsername());

                                    emitter.onComplete();
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@androidx.annotation.NonNull Exception e) {
                                emitter.onError(new Throwable("رجاء التأكد من اسم المستخدم وكلمة المرور"));
                            }
                        });
            }
        });
    }

    public @NonNull Completable addNewElector(Elector elector) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(@NonNull CompletableEmitter emitter) throws Throwable {
                database.collection("electors")
                        .whereEqualTo("id", elector.getId())
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                if (queryDocumentSnapshots.isEmpty()){
                                    database.collection("electors").add(elector)
                                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                @Override
                                                public void onSuccess(DocumentReference documentReference) {
                                                    emitter.onComplete();
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@androidx.annotation.NonNull Exception e) {
                                                    emitter.onError(new Throwable("هناك مشكلة، رجاء حاول مرة اخرى"));
                                                }
                                            });
                                }else {
                                    emitter.onError(new Throwable("هذا التاخب موجود في قاعدة البيانات بالفعل"));
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@androidx.annotation.NonNull Exception e) {
                        emitter.onError(new Throwable("هناك مشكلة، رجاء حاول مرة اخرى"));
                    }
                });
            }
        });
    }

    public @NonNull Flowable<List<Elector>> getRecords() {
        return Flowable.create(new FlowableOnSubscribe<List<Elector>>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<List<Elector>> emitter) throws Throwable {
                database.collection("electors")
                        .whereEqualTo("username", Prefs.getString(Constants.USER_NAME,""))
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                if (queryDocumentSnapshots.isEmpty())return;
                                List<Elector> electors = new ArrayList<>();
                                for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                                    electors.add(documentSnapshot.toObject(Elector.class));
                                }
                                emitter.onNext(electors);
                            }
                        });
            }
        }, BackpressureStrategy.BUFFER);
    }
}
