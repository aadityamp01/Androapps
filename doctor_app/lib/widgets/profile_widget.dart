// ignore_for_file: prefer_const_constructors

import 'dart:io';
import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:flutter/material.dart';
import 'package:hospital_app/utils/storage_service.dart';
import 'package:firebase_storage/firebase_storage.dart';

class ProfileWidget extends StatelessWidget {
  final Image imagepath;
  final bool isEdit;
  final VoidCallback onClicked;

  const ProfileWidget(
      {Key? key,
      required this.imagepath,
      required this.onClicked,
      this.isEdit = false})
      : super(key: key);

  @override
  Widget build(BuildContext context) {
    final color = Theme.of(context).colorScheme.primary;
    return Center(
        child: Stack(
      children: [
        buildImage(),
        Positioned(
          bottom: 0,
          right: 4,
          child: buildEditIcon(color),
        )
      ],
    ));
  }

  Widget buildImage() {
    Image image1 = imagepath;

    return ClipOval(
      child: Material(
        color: Colors.transparent,
        child: Ink.image(
            image: image1.image, //image as ImageProvider,
            fit: BoxFit.cover,
            width: 128,
            height: 128,
            child: InkWell(
              onTap: onClicked,
            )),
      ),
    );
  }

  buildEditIcon(Color color) => buildCircle(
        color: color,
        all: 8,
        child: Icon(
          isEdit ? Icons.add_a_photo : Icons.edit,
          color: Colors.white,
          size: 20,
        ),
      );

  buildCircle(
          {required Color color, required double all, required Icon child}) =>
      ClipOval(
        child: Container(
          padding: EdgeInsets.all(all),
          child: child,
          color: color,
        ),
      );
}
