package com.mydigipay.challenge.framework.di.scopes

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope

@Scope
@Retention(RetentionPolicy.CLASS)
internal annotation class ApplicationScope
