import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';
import 'package:flutter_staggered_grid_view/flutter_staggered_grid_view.dart';
import 'package:get/get.dart';
import 'package:wallpaper_selector/components/search_textfeild.dart';

import '../controller/image_controller.dart';
import 'image_view.dart';

class SearchPage extends StatelessWidget {
  final ImageController imageController = Get.put(ImageController());
  final searchController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    void fetchSearchImages() {
      String searchData = searchController.text;
      FocusScopeNode currentFocus = FocusScope.of(context);
      imageController.fetchSearchResults(searchData);
      if (!currentFocus.hasPrimaryFocus) {
        currentFocus.unfocus();
        // NavBar().keyBoardOpen(false);
      }
    }

    return GestureDetector(
      onTap: () {
        FocusScopeNode currentFocus = FocusScope.of(context);
        if (!currentFocus.hasPrimaryFocus) {
          currentFocus.unfocus();
        }
      },
      child: Scaffold(
        backgroundColor: const Color(0xFFE3FDFD),
        body: SafeArea(
          child: Container(
            margin: const EdgeInsets.only(bottom: 12),
            child: Column(children: [
              const SizedBox(height: 20),
              SearchTextField(
                controller: searchController,
                hintText: 'Search',
                obscureText: false,
                submitted: fetchSearchImages,
              ),
              const SizedBox(height: 10),
              Expanded(
                child: Container(
                  margin: const EdgeInsets.symmetric(horizontal: 12),
                  child: Obx(
                    () => AlignedGridView.count(
                      scrollDirection: Axis.vertical,
                      shrinkWrap: true,
                      crossAxisCount: 2,
                      crossAxisSpacing: 8,
                      mainAxisSpacing: 8,
                      itemCount: imageController.searchResult.length,
                      itemBuilder: (context, index) {
                        return ClipRRect(
                          borderRadius: BorderRadius.circular(8),
                          child: GestureDetector(
                            child: CachedNetworkImage(
                              height: 275,
                              imageUrl: imageController.searchResult[index]
                                  ['urls']['small'],
                              placeholder: (context, url) => const Center(
                                  child: CircularProgressIndicator()),
                              key: UniqueKey(),
                              fit: BoxFit.cover,
                            ),
                            onTap: () => {
                              Navigator.push(
                                  context,
                                  MaterialPageRoute(
                                      builder: (context) => ImageView(
                                            id: imageController
                                                .searchResult[index]['id'],
                                            link: imageController
                                                    .searchResult[index]['urls']
                                                ['full'],
                                            smallLink: imageController
                                                    .searchResult[index]["urls"]
                                                ["small"],
                                          ))),
                            },
                          ),
                        );
                      },
                    ),
                  ),
                ),
              )
            ]),
          ),
        ),
      ),
    );
  }
}
