import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:like_button/like_button.dart';
import 'package:wallpaper_selector/components/glass_box.dart';

import '../controller/image_controller.dart';

class ImageView extends StatefulWidget {
  final String id;
  final String link;
  final String smallLink;
  const ImageView(
      {super.key,
      required this.id,
      required this.link,
      required this.smallLink});
  @override
  State<ImageView> createState() => _ImageViewState();
}

class _ImageViewState extends State<ImageView> {
  bool favorite = false;
  @override
  Widget build(BuildContext context) {
    Future<void> screenDisplay;
    screenDisplay = SystemChrome.setEnabledSystemUIMode(SystemUiMode.manual,
        overlays: [SystemUiOverlay.top]);
    return WillPopScope(
      onWillPop: () async {
        screenDisplay =
            SystemChrome.setEnabledSystemUIOverlays(SystemUiOverlay.values);
        return true;
      },
      child: Scaffold(
          backgroundColor: const Color(0xFFE3FDFD),
          body: Stack(children: [
            Positioned.fill(
              child: CachedNetworkImage(
                imageUrl: widget.link,
                placeholder: (context, url) =>
                    const Center(child: CircularProgressIndicator()),
                key: UniqueKey(),
                fit: BoxFit.cover,
              ),
            ),
            Column(
              children: [
                Expanded(
                  flex: 2,
                  child: Row(
                    children: [
                      const SizedBox(
                        width: 10,
                      ),
                      GlassBox(
                        height: 50.0,
                        width: 50.0,
                        child: IconButton(
                            onPressed: () {
                              screenDisplay =
                                  SystemChrome.setEnabledSystemUIOverlays(
                                      SystemUiOverlay.values);
                              Navigator.pop(context);
                            },
                            icon: const Icon(
                              Icons.arrow_back_rounded,
                              size: 35,
                              color: Colors.white,
                            )),
                      ),
                    ],
                  ),
                ),
                const Expanded(
                  flex: 9,
                  child: SizedBox(),
                ),
                buildTile(),
                const Expanded(
                  flex: 1,
                  child: SizedBox(),
                )
              ],
            ),
          ])),
    );
  }

  Widget buildTile() {
    final alreadySaved = ImageController().check(widget.id);
    Future<bool> onLikeButtonTapped(bool isLiked) async {
      setState(() {
        Map<String, String> imageData = {
          'id': widget.id,
          'link': widget.link,
          'smallLink': widget.smallLink
        };
        ImageController().change(widget.id, imageData, alreadySaved);
      });
      return !isLiked;
    }

    Future<void> setWallpaper() async {
      // int location = WallpaperManager.HOME_SCREEN;
      // var file = await DefaultCacheManager().getSingleFile(widget.link);
      // bool result =
      //     await WallpaperManager.setWallpaperFromFile(file.path, location);
    }
    onDownloadClick() {}
    ;
    double size = 30;
    return GlassBox(
        height: 50.0,
        width: 300.0,
        child: Row(
          children: [
            Expanded(
              child: IconButton(
                  onPressed: setWallpaper,
                  icon: Icon(
                    Icons.wallpaper,
                    color: Colors.white,
                    size: size,
                  )),
            ),
            Expanded(
              child: LikeButton(
                isLiked: alreadySaved,
                onTap: onLikeButtonTapped,
                likeBuilder: (bool isLiked) {
                  return Icon(
                    Icons.favorite,
                    color: isLiked ? const Color(0xFFfb4483) : Colors.white,
                    size: size,
                  );
                },
              ),
            ),
            Expanded(
              child: IconButton(
                  onPressed: onDownloadClick,
                  icon: Icon(
                    Icons.save_alt,
                    color: Colors.white,
                    size: size,
                  )),
            ),
          ],
        ));
  }
}
