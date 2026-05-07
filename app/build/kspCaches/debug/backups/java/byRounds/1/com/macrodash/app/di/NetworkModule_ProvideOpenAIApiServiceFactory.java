package com.macrodash.app.di;

import com.macrodash.app.data.remote.ai.OpenAIApiService;
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
public final class NetworkModule_ProvideOpenAIApiServiceFactory implements Factory<OpenAIApiService> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideOpenAIApiServiceFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public OpenAIApiService get() {
    return provideOpenAIApiService(retrofitProvider.get());
  }

  public static NetworkModule_ProvideOpenAIApiServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideOpenAIApiServiceFactory(retrofitProvider);
  }

  public static OpenAIApiService provideOpenAIApiService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideOpenAIApiService(retrofit));
  }
}
