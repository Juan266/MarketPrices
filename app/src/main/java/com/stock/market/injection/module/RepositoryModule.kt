package com.stock.market.injection.module


import com.stock.market.data.repository.NewsRepositoryImpl
import com.stock.market.data.repository.PanelRepositoryImpl
import com.stock.market.data.repository.SplashRepositoryImpl
import com.stock.market.domain.repository.NewsRepository
import com.stock.market.domain.repository.PanelRepository
import com.stock.market.domain.repository.SplashRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

/*val RepositoryModule = module {
    single<SplashRepository> { SplashRepositoryImpl(get()) }
    single<PanelRepository> { PanelRepositoryImpl(get()) }
}*/

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Provides
    @ActivityRetainedScoped
    internal fun provideSplashRepository(splashRepository: SplashRepositoryImpl): SplashRepository {
        return splashRepository
    }

    @Provides
    @ActivityRetainedScoped
    internal fun providePanelRepository(panelRepository: PanelRepositoryImpl): PanelRepository {
        return panelRepository
    }

    @Provides
    @ActivityRetainedScoped
    internal fun provideNewsRepository(newsRepository: NewsRepositoryImpl): NewsRepository {
        return newsRepository
    }
}