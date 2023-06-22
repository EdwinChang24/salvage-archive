package io.github.edwinchang24.salvage.database

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {
    @Provides
    fun providesItemDao(database: SalvageDatabase) = database.itemDao()
}
