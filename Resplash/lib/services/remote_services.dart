import 'dart:convert';

import 'package:http/http.dart' as http;
import 'package:wallpaper_selector/model/image_model.dart';

class RemoteServices{
  static var client = http.Client();

  static Future<List<Welcome>> fetchImages(int pageNum) async{
    var data = <Welcome>[];
    var response = await client.get(Uri.parse('https://api.unsplash.com/photos?page=$pageNum&per_page=30&client_id='));
    if (response.statusCode == 200){
      var jsonString = response.body;
      data = welcomeFromJson(jsonString);
      return data;
    }
    else{
      return data;
    }
  }

  static Future<List> fetchSearchResults(String search) async{
    var data = <Map>[];
    var response = await client.get(Uri.parse('https://api.unsplash.com/search/photos?query=$search&per_page=30&client_id='));
    if (response.statusCode == 200){
      var jsonString = response.body;
      var data = jsonDecode(jsonString);
      var returnData = data['results'];
      return returnData;
    }
    else{
      return data;
    }
  }

  Future<String> fetchImage(String id) async {
    var client = http.Client();
    var response = await client.get(Uri.parse(
        "https://api.unsplash.com/photos/$id/?client_id="));
    if (response.statusCode == 200) {
      var jsonString = response.body;
      final data = jsonDecode(jsonString);
      return data['urls']['regular'];
    } else {
      return '';
    }
  }


}