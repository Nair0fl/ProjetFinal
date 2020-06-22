package com.example.projetfinal.di

import android.app.Application
import android.content.Context
import com.example.projetfinal.helpers.MemoRoomDatabaseHelpers
import com.example.projetfinal.models.MemoDAO
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule
{
    @Provides
    fun provideContext( application: Application):Context
    {
        return application;
    }

    @Singleton
    @Provides
    fun provideMemoDAO(context : Context):MemoDAO
    {
        return MemoRoomDatabaseHelpers.getDatabase(context).memoDao()

    }

}