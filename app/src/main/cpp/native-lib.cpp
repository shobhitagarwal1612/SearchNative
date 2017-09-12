#include <jni.h>
#include <string>

namespace searchnative_example_com_searchnative {

    jstring stringFromJNI(JNIEnv *env, jobject obj,
                          jstring textString, jstring stringToSearch) {
        char *tmp;
        bool wordSearch;

        char *nativeTextString = (char *) env->GetStringUTFChars(textString, JNI_FALSE);
        char *nativeSearchText = (char *) env->GetStringUTFChars(stringToSearch, JNI_FALSE);

        tmp = strstr(nativeTextString, nativeSearchText);

        wordSearch = tmp != NULL;

        std::string result = "Hello from C++";
        if (wordSearch) {
            result += "\nWord found";
        } else {
            result += "\nWord not found";
        }

        return env->NewStringUTF(result.c_str());
    }

    static JNINativeMethod method_table[] = {
            {"stringFromJNI", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", (void *) stringFromJNI}
    };
}

using namespace searchnative_example_com_searchnative;

extern "C" jint JNI_OnLoad(JavaVM *vm, void *reserved) {
    JNIEnv *env;
    if (vm->GetEnv(reinterpret_cast<void **>(&env), JNI_VERSION_1_6) != JNI_OK) {
        return -1;
    } else {
        jclass clazz = env->FindClass("searchnative/example/com/searchnative/SearchLib");
        if (clazz) {
            env->RegisterNatives(clazz, method_table,
                                 sizeof(method_table) / sizeof(method_table[0]));
            env->DeleteLocalRef(clazz);
            return JNI_VERSION_1_6;
        } else {
            return -1;
        }
    }
}