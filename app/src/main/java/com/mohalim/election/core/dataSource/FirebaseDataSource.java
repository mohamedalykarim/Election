package com.mohalim.election.core.dataSource;

import android.app.Application;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.mohalim.election.core.models.UserItem;
import com.mohalim.election.core.utils.Constants;
import com.pixplicity.easyprefs.library.Prefs;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableEmitter;
import io.reactivex.rxjava3.core.CompletableOnSubscribe;

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
                                    Prefs.putString(Constants.NAME, userItem.getUsername());
                                    Prefs.putString(Constants.USER_NAME, userItem.getUsername());

                                    emitter.onComplete();
                                    emitter.onError(new Throwable("تم تسجيل الدخول"));
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
}
