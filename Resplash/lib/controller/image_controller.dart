import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:get/state_manager.dart';
import 'package:http/http.dart' as http;
import 'package:wallpaper_selector/model/image_model.dart';
import 'package:wallpaper_selector/services/remote_services.dart';

class ImageController extends GetxController {
  var imageList = <Welcome>[].obs;
  static var savedImagesID = <String>{}.obs;
  static var savedImagesData = <Map>{}.obs;
  final currentUser = FirebaseAuth.instance;
  var searchResult = [].obs;
  static var client = http.Client();
  @override
  void onInit() {
    // TODO: implement onInit
    fetchImages(1);
    fetchImages(2);
    fetchImages(3);
    super.onInit();
  }

  void fetchSearchResults(String search) async {
    var images = await RemoteServices.fetchSearchResults(search);
    if (images.isNotEmpty) {
      searchResult.clear();
      searchResult.addAll(images);
    }
  }

  void fetchImages(int pageNum) async {
    var images = await RemoteServices.fetchImages(pageNum);
    if (images.isNotEmpty) {
      imageList.addAll(images);
    }
  }

  bool check(String id) {
    return savedImagesID.contains(id);
  }

  getData() async {
    await FirebaseFirestore.instance
        .collection("UserData/${currentUser.currentUser?.uid}/Images")
        .get()
        .then((QuerySnapshot querySnapshot) {
      for (var doc in querySnapshot.docs) {
        savedImagesID.addIf(!savedImagesID.contains(doc["id"]), doc["id"]);
        savedImagesData.addIf(
            !savedImagesData.contains({
              "id": doc['id'],
              "link": doc['link'],
              "smallLink": doc["smallLink"]
            }),
            {
              "id": doc['id'],
              "link": doc['link'],
              "smallLink": doc["smallLink"]
            });
      }
    });
  }

  void change(String id, Map<String, String> data, bool check) {
    if (check) {
      FirebaseFirestore.instance
          .collection("UserData/${currentUser.currentUser?.uid}/Images")
          .doc(id)
          .delete();
      savedImagesID.remove(id);
      savedImagesData.removeWhere((element) => element['id'] == id);
    } else {
      FirebaseFirestore.instance
          .collection("UserData/${currentUser.currentUser?.uid}/Images")
          .doc(id)
          .set(data);
      savedImagesID.add(id);
      savedImagesData.add(data);
    }
    print(savedImagesData);
  }

  void clear() {
    savedImagesData.clear();
    savedImagesID.clear();
  }
}
