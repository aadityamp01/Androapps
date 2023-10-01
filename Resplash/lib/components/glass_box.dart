import 'dart:ui';

import 'package:flutter/material.dart';

class GlassBox extends StatelessWidget {
  final height;
  final width;
  final child;
  const GlassBox(
      {super.key,
      required this.child,
      required this.height,
      required this.width});

  @override
  Widget build(BuildContext context) {
    return Center(
      child: ClipRRect(
        borderRadius: BorderRadius.circular(10),
        child: Stack(children: [
          BackdropFilter(
            filter: ImageFilter.blur(
              sigmaX: 2,
              sigmaY: 2,
            ),
            child: const SizedBox(height: 0, width: 0),
          ),
          Container(
            height: height,
            width: width,
            decoration: BoxDecoration(
                boxShadow: [
                  BoxShadow(
                    color: Colors.black.withOpacity(0.1),
                  )
                ],
                border: Border.all(color: Colors.white.withOpacity(0.1)),
                gradient: LinearGradient(
                  begin: Alignment.topLeft,
                  end: Alignment.bottomRight,
                  colors: [
                    Colors.white.withOpacity(0.3),
                    Colors.white.withOpacity(0.3)
                  ],
                ),
                borderRadius: BorderRadius.circular(10)),
            child: child,
          ),
        ]),
      ),
    );
  }
}
