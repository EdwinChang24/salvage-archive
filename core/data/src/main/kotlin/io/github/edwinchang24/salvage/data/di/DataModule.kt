package io.github.edwinchang24.salvage.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.edwinchang24.salvage.data.repository.DefaultItemRepository
import io.github.edwinchang24.salvage.data.repository.ItemRepository

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindsItemRepository(itemRepository: DefaultItemRepository): ItemRepository
}
