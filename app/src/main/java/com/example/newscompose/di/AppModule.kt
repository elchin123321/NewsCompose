package com.example.newscompose.di

import dagger.Module

@Module(includes = [NetworkModule::class, AppBindModule::class])
interface AppModule