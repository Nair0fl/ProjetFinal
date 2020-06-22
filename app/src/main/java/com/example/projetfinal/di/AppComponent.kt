package com.example.projetfinal.di

import android.app.Application
import com.example.projetfinal.activitys.MainActivity
import com.example.projetfinal.helpers.MemoTouchHelperCallback
import com.example.projetfinal.repositories.MemoRepository
import com.example.projetfinal.viewmodels.MemoListAdapter
import dagger.BindsInstance
import dagger.Component
import dagger.Provides
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent?
    }

    fun inject(memoRepository: MemoRepository)
    fun inject(memoListAdapter: MemoListAdapter)

}