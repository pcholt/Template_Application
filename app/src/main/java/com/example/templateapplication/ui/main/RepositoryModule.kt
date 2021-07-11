package com.example.templateapplication.ui.main

import dagger.Binds
import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.multibindings.IntoMap
import dagger.multibindings.IntoSet
import javax.inject.Qualifier

@Qualifier
annotation class ConfigurationString

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsSimpleRepository(repositoryImpl: RepositoryImpl): SimpleRepository

    companion object {
        @Provides
        @ConfigurationString
        fun providesConfig() = "HEYO"

    }
}
