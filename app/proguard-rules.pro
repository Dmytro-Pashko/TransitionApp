-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontpreverify
-verbose

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
