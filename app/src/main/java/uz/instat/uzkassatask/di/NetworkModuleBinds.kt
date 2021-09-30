package uz.instat.fitneskittest.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.instat.fitneskittest.busines.network.abstraction.MainService
import uz.instat.fitneskittest.busines.network.implementation.MainServiceImpl
import uz.instat.uzkassatask.framework.MainRepo
import uz.instat.uzkassatask.framework.MainRepoImpl
import uz.instat.uzkassatask.framework.datasource.MainDataSource
import uz.instat.uzkassatask.framework.datasource.MainDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModuleBinds {


    @Singleton
    @Binds
    abstract fun bindMainRepository(mainRepositoryImpl: MainRepoImpl): MainRepo


    @Singleton
    @Binds
    abstract fun bindMainService(mainServiceImpl: MainServiceImpl): MainService


    @Singleton
    @Binds
    abstract fun bindMainDataSource(mainDataSourceImpl: MainDataSourceImpl): MainDataSource

}