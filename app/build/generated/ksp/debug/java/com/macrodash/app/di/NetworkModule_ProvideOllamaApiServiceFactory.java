package com.macrodash.app.di;

import com.macrodash.app.data.remote.ai.OllamaApiService;
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
public final class NetworkModule_ProvideOllamaApiServiceFactory implements Factory<OllamaApiService> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideOllamaApiServiceFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public OllamaApiService get() {
    return provideOllamaApiService(retrofitProvider.get());
  }

  public static NetworkModule_ProvideOllamaApiServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideOllamaApiServiceFactory(retrofitProvider);
  }

  public static OllamaApiService provideOllamaApiService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideOllamaApiService(retrofit));
  }
}
