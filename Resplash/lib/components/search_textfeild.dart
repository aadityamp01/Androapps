import 'package:flutter/material.dart';

class SearchTextField extends StatelessWidget {
  final controller;
  final String hintText;
  final bool obscureText;
  final submitted;

  const SearchTextField({
    super.key,
    required this.controller,
    required this.hintText,
    required this.obscureText,
    required this.submitted,
  });

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 25.0),
      child: TextField(
        cursorColor: Colors.black,
        style: const TextStyle(color: Colors.white),
        onEditingComplete: submitted,
        textInputAction: TextInputAction.search,
        controller: controller,
        obscureText: obscureText,
        decoration: InputDecoration(
          contentPadding: const EdgeInsets.all(18),
          enabledBorder: const OutlineInputBorder(
            borderSide: BorderSide(color: Color(0xFFCBF1F5)),
            borderRadius: BorderRadius.all(Radius.circular(20)),
          ),
          focusedBorder: const OutlineInputBorder(
            borderSide: BorderSide(color: Color(0xFF71C9CE)),
            borderRadius: BorderRadius.all(Radius.circular(20)),
          ),
          fillColor: const Color(0xFF71C9CE),
          filled: true,
          hintText: hintText,
          hintStyle: const TextStyle(color: Colors.white),
        ),
      ),
    );
  }
}
