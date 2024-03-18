-dontpreverify
-flattenpackagehierarchy 'myobfuscated'
-keepattributes SourceFile,LineNumberTable,Signature,*Annotation*,InnerClasses,Exceptions

# Prevent proguard from stripping interface information from TypeAdapterFactory,
# JsonSerializer, JsonDeserializer, TypeAdapter instances (so they can be used in @JsonAdapter)
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer
-keep class * extends com.google.gson.TypeAdapter
-keep,allowobfuscation,allowshrinking class com.google.gson.reflect.TypeToken
-keep,allowobfuscation,allowshrinking class * extends com.google.gson.reflect.TypeToken

-keep public class com.google.android.gms.ads.** {
   public *;
}

-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}


# Prevent R8 from leaving Data object members always null (Serializable)
-keepclassmembers,allowobfuscation class * {
  @com.google.gson.annotations.SerializedName <fields>;
}
-keepnames class * implements java.io.Serializable
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    !private <fields>;
    !private <methods>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}


-keep class com.inmobi.** { *; }

# skip the Picasso library classes
-keep class com.squareup.picasso.** {*;}

# skip Moat classes
-keep class com.moat.** {*;}

# skip AVID classes
-keep class com.integralads.avid.library.* {*;}

# (Retrofit) Platform calls Class.forName on types which do not exist on Android to determine platform.
-keep class retrofit2.** { *; }
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}
-keepclasseswithmembers interface * {
    @retrofit2.* <methods>;
}


# coroutines
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
-keep class kotlinx.coroutines.android.AndroidDispatcherFactory {*;}

# Most of volatile fields are updated with AFU and should not be mangled
-keepclassmembernames class kotlinx.** {
    volatile <fields>;
}

#crashlytics
-keep public class * extends java.lang.Exception

-keep class com.kakao.sdk.**.model.* { <fields>; }

# fresco bug https://github.com/facebook/fresco/issues/2638

-dontwarn com.flurry.android.Consent
-dontwarn com.flurry.android.FlurryAgent$Builder
-dontwarn com.flurry.android.FlurryAgent
-dontwarn com.flurry.android.FlurryConsent
-dontwarn com.flurry.android.FlurryPublisherSegmentation
-dontwarn com.tencent.tauth.IUiListener
-dontwarn com.tencent.tauth.Tencent

# Needed for notifications settings and preferences
-keepnames public class * extends android.preference.Preference


-keepclassmembers,allowobfuscation class * {
 @com.google.gson.annotations.SerializedName <fields>;
}
-if interface * { @retrofit2.http.* public *** *(...); }
-keep,allowoptimization,allowshrinking,allowobfuscation class <3>

-keepattributes Signature

-keep class java.lang.invoke.StringConcatFactory {*;}
-dontwarn java.lang.invoke.StringConcatFactory
-keep class java.lang.invoke.StringConcatFactory
-keep,allowobfuscation,allowshrinking class com.google.devtools.ksp.processing.SymbolProcessorProvider
-keep class com.google.devtools.ksp.processing.SymbolProcessorProvider
-dontwarn com.google.devtools.ksp.processing.SymbolProcessorProvider
-keep,allowobfuscation,allowshrinking interface **.ExcludeCoverageGenerated
-dontwarn **.ExcludeCoverageGenerated

 # https://github.com/square/okhttp/blob/339732e3a1b78be5d792860109047f68a011b5eb/okhttp/src/jvmMain/resources/META-INF/proguard/okhttp3.pro#L11-L14
 -dontwarn okhttp3.internal.platform.**
 -dontwarn org.conscrypt.**
 -dontwarn org.bouncycastle.**
 -dontwarn org.openjsse.**

 #  Waiting for new retrofit release to remove these rules (R8 full mode strips signatures from non-kept items).
  # With R8 full mode generic signatures are stripped for classes that are not
  # kept. Suspend functions are wrapped in continuations where the type argument
  # is used.
 -keep,allowobfuscation,allowshrinking interface retrofit2.Call
 -keep,allowobfuscation,allowshrinking class retrofit2.Response
 -keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation

 -keep,allowobfuscation,allowshrinking class com.your.company.YourCustomSealedClass

 -dontwarn com.google.android.material.R$id


# added for kmm editor foundation library
 -dontwarn kotlinx.serialization.DeserializationStrategy
 -dontwarn kotlinx.serialization.KSerializer
 -dontwarn kotlinx.serialization.SerialName
 -dontwarn kotlinx.serialization.Serializable
 -dontwarn kotlinx.serialization.SerializationStrategy
 -dontwarn kotlinx.serialization.StringFormat
 -dontwarn kotlinx.serialization.UnknownFieldException
 -dontwarn kotlinx.serialization.builtins.BuiltinSerializersKt
 -dontwarn kotlinx.serialization.descriptors.SerialDescriptor
 -dontwarn kotlinx.serialization.encoding.CompositeDecoder
 -dontwarn kotlinx.serialization.encoding.CompositeEncoder
 -dontwarn kotlinx.serialization.encoding.Decoder
 -dontwarn kotlinx.serialization.encoding.Encoder
 -dontwarn kotlinx.serialization.internal.ArrayListSerializer
 -dontwarn kotlinx.serialization.internal.BooleanSerializer
 -dontwarn kotlinx.serialization.internal.FloatSerializer
 -dontwarn kotlinx.serialization.internal.GeneratedSerializer$DefaultImpls
 -dontwarn kotlinx.serialization.internal.GeneratedSerializer
 -dontwarn kotlinx.serialization.internal.PluginExceptionsKt
 -dontwarn kotlinx.serialization.internal.PluginGeneratedSerialDescriptor
 -dontwarn kotlinx.serialization.internal.SerializationConstructorMarker
 -dontwarn kotlinx.serialization.internal.StringSerializer
 -dontwarn kotlinx.serialization.json.Json$Default
 -dontwarn kotlinx.serialization.json.Json
 -dontwarn kotlinx.serialization.json.JsonBuilder
 -dontwarn kotlinx.serialization.json.JsonKt
 -dontwarn kotlinx.serialization.modules.SerializersModule
