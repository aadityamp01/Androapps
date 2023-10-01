import 'package:curved_navigation_bar/curved_navigation_bar.dart';
import 'package:flutter/material.dart';
import 'package:wallpaper_selector/view/home_page.dart';
import 'package:wallpaper_selector/view/search_page.dart';

import 'liked_page.dart';

class NavBar extends StatefulWidget {
  const NavBar({Key? key}) : super(key: key);

  @override
  State<NavBar> createState() => _NavBarState();
}

class _NavBarState extends State<NavBar> {
  int selectedIndex = 0;
  @override
  Widget build(BuildContext context) {
    void navigateButton(int index) {
      setState(() {
        selectedIndex = index;
      });
    }

    final List<Widget> pages = [HomePage(), SearchPage(), LikedPage()];
    return Scaffold(
        resizeToAvoidBottomInset: false,
        backgroundColor: const Color(0xFFE3FDFD),
        body: pages[selectedIndex],
        bottomNavigationBar: CurvedNavigationBar(
          height: 60,
          color: const Color(0xFF71C9CE),
          backgroundColor: Colors.transparent,
          animationDuration: const Duration(milliseconds: 300),
          onTap: navigateButton,
          index: selectedIndex,
          items: const [
            Icon(
              Icons.home,
              color: Colors.white,
            ),
            Icon(
              Icons.search,
              color: Colors.white,
            ),
            Icon(
              Icons.favorite,
              color: Colors.white,
            ),
          ],
        ));
  }
}
