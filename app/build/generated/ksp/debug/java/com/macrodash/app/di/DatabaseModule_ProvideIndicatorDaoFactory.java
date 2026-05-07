package com.macrodash.app.di;

import com.macrodash.app.data.local.MacroDashDatabase;
import com.macrodash.app.data.local.dao.IndicatorDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
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
public final class DatabaseModule_ProvideIndicatorDaoFactory implements Factory<IndicatorDao> {
  private final Provider<MacroDashDatabase> databaseProvider;

  public DatabaseModule_ProvideIndicatorDaoFactory(Provider<MacroDashDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public IndicatorDao get() {
    return provideIndicatorDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideIndicatorDaoFactory create(
      Provider<MacroDashDatabase> databaseProvider) {
    return new DatabaseModule_ProvideIndicatorDaoFactory(databaseProvider);
  }

  public static IndicatorDao provideIndicatorDao(MacroDashDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideIndicatorDao(database));
  }
}
