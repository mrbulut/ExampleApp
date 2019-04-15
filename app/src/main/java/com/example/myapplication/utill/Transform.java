package com.example.myapplication.utill;

import android.graphics.Color;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Transformation;

public class Transform {
    private static Transformation transformation;

    public static Transformation set(){

        transformation = new RoundedTransformationBuilder()
                .borderColor(Color.BLACK)
                .borderWidthDp(0)
                .cornerRadiusDp(14)
                .oval(false)
                .build();

        return transformation;
    }
}
