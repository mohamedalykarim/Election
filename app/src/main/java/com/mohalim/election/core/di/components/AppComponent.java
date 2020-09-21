package com.mohalim.election.core.di.components;


import android.app.Application;


import com.mohalim.election.core.di.base.BaseApplication;
import com.mohalim.election.core.di.modules.ActivityBuildersModule;
import com.mohalim.election.core.di.modules.AppModule;
import com.mohalim.election.core.di.modules.ServiceBuilderModule;
import com.mohalim.election.core.di.modules.ViewmodelFactoryModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                ActivityBuildersModule.class,
                ServiceBuilderModule.class,
                AppModule.class,
                ViewmodelFactoryModule.class
        }
)
public interface AppComponent extends AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder getInstance(Application application);

        AppComponent build();
    }
}