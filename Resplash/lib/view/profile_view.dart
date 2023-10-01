import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';

class ProfileView extends StatelessWidget {
  final Function()? onPressed;
  const ProfileView({super.key, required this.onPressed});

  @override
  Widget build(BuildContext context) {
    return ClipRRect(
      borderRadius: BorderRadius.circular(15),
      child: Container(
        height: 120,
        width: 400,
        color: const Color(0xFF71C9CE),
        child: Row(
          children: [
            const SizedBox(
              width: 10,
            ),
            Container(
              height: 100,
              decoration: BoxDecoration(
                  border: Border.all(color: const Color(0xFFCBF1F5), width: 3),
                  borderRadius: BorderRadius.circular(50)),
              child: ClipOval(
                child: Image.asset("lib/images/DP0.1.jpg"),
              ),
            ),
            const SizedBox(width: 10),
            Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Expanded(
                    child: Container(
                        margin: const EdgeInsets.only(top: 20),
                        child: Text("Aadhithya D",
                            style: GoogleFonts.bebasNeue(
                                color: Colors.white, fontSize: 38)))),
                Expanded(
                  child: Container(
                    margin: const EdgeInsets.only(top: 2),
                    child: Text("aadhithyasan024@gmail.com",
                        style: GoogleFonts.oswald(color: Colors.white)),
                  ),
                )
              ],
            ),
            Container(
              margin: const EdgeInsets.only(left: 45, bottom: 60),
              child: IconButton(
                  onPressed: onPressed,
                  icon: const Icon(
                    Icons.logout,
                    color: Colors.white,
                    size: 20,
                  )),
            )
          ],
        ),
      ),
    );
  }
}
