package com.dpashko.transitionapp.extension;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import dagger.android.AndroidInjector;
import dagger.android.HasAndroidInjector;

import static dagger.internal.Preconditions.checkNotNull;

public final class AndroidInjection {

    public static void inject(Fragment fragment) {
        checkNotNull(fragment, "fragment");
        HasAndroidInjector hasAndroidInjector = findHasAndroidInjectorForFragment(fragment);
        inject(fragment, hasAndroidInjector);
    }

    private static HasAndroidInjector findHasAndroidInjectorForFragment(Fragment fragment) {
        Fragment parentFragment = fragment;
        while ((parentFragment = parentFragment.getParentFragment()) != null) {
            if (parentFragment instanceof HasAndroidInjector) {
                return (HasAndroidInjector) parentFragment;
            }
        }
        FragmentActivity activity = fragment.getActivity();
        if (activity instanceof HasAndroidInjector) {
            return (HasAndroidInjector) activity;
        }
        if (activity.getApplication() instanceof HasAndroidInjector) {
            return (HasAndroidInjector) activity.getApplication();
        }
        throw new IllegalArgumentException(
                String.format("No injector was found for %s", fragment.getClass().getCanonicalName()));
    }

    private static void inject(Object target, HasAndroidInjector hasAndroidInjector) {
        AndroidInjector<Object> androidInjector = hasAndroidInjector.androidInjector();
        checkNotNull(
                androidInjector, "%s.androidInjector() returned null", hasAndroidInjector.getClass());

        androidInjector.inject(target);
    }

    private AndroidInjection() {
    }
}
