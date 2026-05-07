package com.macrodash.app.di;

import com.macrodash.app.data.remote.yahoo.YahooDataSource;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("javax.inject.Named")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class NetworkModule_ProvideYahooDataSourceFactory implements Factory<YahooDataSource> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideYahooDataSourceFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public YahooDataSource get() {
    return provideYahooDataSource(retrofitProvider.get());
  }

  public static NetworkModule_ProvideYahooDataSourceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideYahooDataSourceFactory(retrofitProvider);
  }

  public static YahooDataSource provideYahooDataSource(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideYahooDataSource(retrofit));
  }
}
