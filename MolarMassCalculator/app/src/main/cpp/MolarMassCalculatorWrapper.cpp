//
// Created by Shahriar Nasim Nafi on 11/10/21.
// Copyright © 2021 Shahriar Nasim Nafi. All rights reserved.
//


#include <jni.h>
#include <string>
#include "molar_mass_calculator.h"

MolarMassCalculator molarMassCalculator = MolarMassCalculator();

extern "C" JNIEXPORT jdouble
Java_snnafi_molar_mass_calculator_adapter_ElementInfoAdapter_getElementMolarMassBySymbol(
        JNIEnv *env, jobject,
        jstring symbol) {
    return molarMassCalculator.getElementMolarMassBySymbol(env->GetStringUTFChars(symbol, nullptr));
}

extern "C" JNIEXPORT jstring
Java_snnafi_molar_mass_calculator_MainActivity_getMolarMass(JNIEnv *env, jobject, jstring formula) {
    return env->NewStringUTF(
            molarMassCalculator.getMolarMass(env->GetStringUTFChars(formula, nullptr)).c_str());
}


jobject createElementInfoKotlin(JNIEnv *env, ElementInfo elementInfo) {
    jclass elementInfoKotlin = env->FindClass(
            "snnafi/molar/mass/calculator/model/ElementInfoKotlin");
    jobject newElementInfoKotlin = env->AllocObject(elementInfoKotlin);


    jfieldID symbolField = env->GetFieldID(elementInfoKotlin, "symbol", "Ljava/lang/String;");
    jfieldID numberField = env->GetFieldID(elementInfoKotlin, "number", "I");

    env->SetObjectField(newElementInfoKotlin, symbolField,
                        env->NewStringUTF(elementInfo.name.c_str()));
    env->SetIntField(newElementInfoKotlin, numberField, elementInfo.number);

    return newElementInfoKotlin;
}

jobject vectorToArrayList(JNIEnv *env, vector<ElementInfo> elementInfos) {
    jclass clazz = (*env).FindClass("java/util/ArrayList");
    jobject object = (*env).NewObject(clazz, (*env).GetMethodID(clazz, "<init>", "()V"));

    for (int i = 0; i < elementInfos.size(); i++) {

        jobject jobject = createElementInfoKotlin(env, elementInfos[i]);

        (*env).CallBooleanMethod(object, (*env).GetMethodID(clazz, "add", "(Ljava/lang/Object;)Z"),
                                 jobject);

    }

    return object;
}

extern "C" JNIEXPORT jobject
Java_snnafi_molar_mass_calculator_MainActivity_getResult(JNIEnv *env, jobject) {
    return vectorToArrayList(env, molarMassCalculator.getResult());
}

extern "C" JNIEXPORT void
Java_snnafi_molar_mass_calculator_MainActivity_performCalculation(JNIEnv *env, jobject,
                                                                  jstring formula) {
    molarMassCalculator.performCalculation(env->GetStringUTFChars(formula, nullptr));
}

extern "C" JNIEXPORT jdouble
Java_snnafi_molar_mass_calculator_MainActivity_getCalculatedMolarMass(JNIEnv *env, jobject) {
    return molarMassCalculator.getMolarMass();
}

extern "C" JNIEXPORT jboolean
Java_snnafi_molar_mass_calculator_MainActivity_isWrongFormula(JNIEnv *env, jobject) {
    return molarMassCalculator.isWrongFormula();
}