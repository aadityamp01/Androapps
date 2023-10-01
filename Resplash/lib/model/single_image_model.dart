// To parse this JSON data, do
//
//     final welcome = welcomeFromJson(jsonString);

import 'dart:convert';

Welcome1 singleWelcomeFromJson(String str) => Welcome1.fromJson(json.decode(str));

class Welcome1 {
  Welcome1({
    required this.id,
    required this.createdAt,
    required this.updatedAt,
    required this.promotedAt,
    required this.width,
    required this.height,
    required this.color,
    required this.blurHash,
    required this.description,
    required this.altDescription,
    required this.urls,
    required this.links,
    required this.likes,
    required this.likedByUser,
    required this.currentUserCollections,
    required this.sponsorship,
    required this.topicSubmissions,
    required this.user,
    required this.exif,
    required this.location,
    required this.meta,
    required this.publicDomain,
    required this.tags,
    required this.tagsPreview,
    required this.views,
    required this.downloads,
    required this.topics,
    required this.relatedCollections,
  });

  String id;
  DateTime createdAt;
  DateTime updatedAt;
  DateTime promotedAt;
  int width;
  int height;
  String color;
  String blurHash;
  dynamic description;
  String altDescription;
  Urls urls;
  WelcomeLinks links;
  int likes;
  bool likedByUser;
  List<dynamic> currentUserCollections;
  dynamic sponsorship;
  WelcomeTopicSubmissions topicSubmissions;
  User user;
  Exif exif;
  Location location;
  Meta meta;
  bool publicDomain;
  List<TagsPreviewElement> tags;
  List<TagsPreviewElement> tagsPreview;
  int views;
  int downloads;
  List<dynamic> topics;
  RelatedCollections relatedCollections;

  factory Welcome1.fromJson(Map<String, dynamic> json) => Welcome1(
    id: json["id"],
    createdAt: DateTime.parse(json["created_at"]),
    updatedAt: DateTime.parse(json["updated_at"]),
    promotedAt: DateTime.parse(json["promoted_at"]),
    width: json["width"],
    height: json["height"],
    color: json["color"],
    blurHash: json["blur_hash"],
    description: json["description"],
    altDescription: json["alt_description"],
    urls: Urls.fromJson(json["urls"]),
    links: WelcomeLinks.fromJson(json["links"]),
    likes: json["likes"],
    likedByUser: json["liked_by_user"],
    currentUserCollections: List<dynamic>.from(json["current_user_collections"].map((x) => x)),
    sponsorship: json["sponsorship"],
    topicSubmissions: WelcomeTopicSubmissions.fromJson(json["topic_submissions"]),
    user: User.fromJson(json["user"]),
    exif: Exif.fromJson(json["exif"]),
    location: Location.fromJson(json["location"]),
    meta: Meta.fromJson(json["meta"]),
    publicDomain: json["public_domain"],
    tags: List<TagsPreviewElement>.from(json["tags"].map((x) => TagsPreviewElement.fromJson(x))),
    tagsPreview: List<TagsPreviewElement>.from(json["tags_preview"].map((x) => TagsPreviewElement.fromJson(x))),
    views: json["views"],
    downloads: json["downloads"],
    topics: List<dynamic>.from(json["topics"].map((x) => x)),
    relatedCollections: RelatedCollections.fromJson(json["related_collections"]),
  );


}

class Exif {
  Exif({
    required this.make,
    required this.model,
    required this.name,
    required this.exposureTime,
    required this.aperture,
    required this.focalLength,
    required this.iso,
  });

  dynamic make;
  dynamic model;
  dynamic name;
  dynamic exposureTime;
  dynamic aperture;
  dynamic focalLength;
  dynamic iso;

  factory Exif.fromJson(Map<String, dynamic> json) => Exif(
    make: json["make"],
    model: json["model"],
    name: json["name"],
    exposureTime: json["exposure_time"],
    aperture: json["aperture"],
    focalLength: json["focal_length"],
    iso: json["iso"],
  );

  Map<String, dynamic> toJson() => {
    "make": make,
    "model": model,
    "name": name,
    "exposure_time": exposureTime,
    "aperture": aperture,
    "focal_length": focalLength,
    "iso": iso,
  };
}

class WelcomeLinks {
  WelcomeLinks({
    required this.self,
    required this.html,
    required this.download,
    required this.downloadLocation,
  });

  String self;
  String html;
  String download;
  String downloadLocation;

  factory WelcomeLinks.fromJson(Map<String, dynamic> json) => WelcomeLinks(
    self: json["self"],
    html: json["html"],
    download: json["download"],
    downloadLocation: json["download_location"],
  );

  Map<String, dynamic> toJson() => {
    "self": self,
    "html": html,
    "download": download,
    "download_location": downloadLocation,
  };
}

class Location {
  Location({
    required this.name,
    required this.city,
    required this.country,
    required this.position,
  });

  String name;
  String city;
  String country;
  Position position;

  factory Location.fromJson(Map<String, dynamic> json) => Location(
    name: json["name"],
    city: json["city"],
    country: json["country"],
    position: Position.fromJson(json["position"]),
  );

  Map<String, dynamic> toJson() => {
    "name": name,
    "city": city,
    "country": country,
    "position": position.toJson(),
  };
}

class Position {
  Position({
    required this.latitude,
    required this.longitude,
  });

  double latitude;
  double longitude;

  factory Position.fromJson(Map<String, dynamic> json) => Position(
    latitude: json["latitude"].toDouble(),
    longitude: json["longitude"].toDouble(),
  );

  Map<String, dynamic> toJson() => {
    "latitude": latitude,
    "longitude": longitude,
  };
}

class Meta {
  Meta({
    required this.index,
  });

  bool index;

  factory Meta.fromJson(Map<String, dynamic> json) => Meta(
    index: json["index"],
  );

  Map<String, dynamic> toJson() => {
    "index": index,
  };
}

class RelatedCollections {
  RelatedCollections({
    required this.total,
    required this.type,
    required this.results,
  });

  int total;
  String type;
  List<Result> results;

  factory RelatedCollections.fromJson(Map<String, dynamic> json) => RelatedCollections(
    total: json["total"],
    type: json["type"],
    results: List<Result>.from(json["results"].map((x) => Result.fromJson(x))),
  );


}

class Result {
  Result({
    required this.id,
    required this.title,
    required this.description,
    required this.publishedAt,
    required this.lastCollectedAt,
    required this.updatedAt,
    required this.curated,
    required this.featured,
    required this.totalPhotos,
    required this.private,
    required this.shareKey,
    required this.tags,
    required this.links,
    required this.user,
    required this.coverPhoto,
    required this.previewPhotos,
  });

  String id;
  String title;
  String description;
  DateTime publishedAt;
  DateTime lastCollectedAt;
  DateTime updatedAt;
  bool curated;
  bool featured;
  int totalPhotos;
  bool private;
  String shareKey;
  List<ResultTag> tags;
  ResultLinks links;
  User user;
  ResultCoverPhoto coverPhoto;
  List<PreviewPhoto> previewPhotos;

  factory Result.fromJson(Map<String, dynamic> json) => Result(
    id: json["id"],
    title: json["title"],
    description: json["description"],
    publishedAt: DateTime.parse(json["published_at"]),
    lastCollectedAt: DateTime.parse(json["last_collected_at"]),
    updatedAt: DateTime.parse(json["updated_at"]),
    curated: json["curated"],
    featured: json["featured"],
    totalPhotos: json["total_photos"],
    private: json["private"],
    shareKey: json["share_key"],
    tags: List<ResultTag>.from(json["tags"].map((x) => ResultTag.fromJson(x))),
    links: ResultLinks.fromJson(json["links"]),
    user: User.fromJson(json["user"]),
    coverPhoto: ResultCoverPhoto.fromJson(json["cover_photo"]),
    previewPhotos: List<PreviewPhoto>.from(json["preview_photos"].map((x) => PreviewPhoto.fromJson(x))),
  );


}

class ResultCoverPhoto {
  ResultCoverPhoto({
    required this.id,
    required this.createdAt,
    required this.updatedAt,
    required this.promotedAt,
    required this.width,
    required this.height,
    required this.color,
    required this.blurHash,
    required this.description,
    required this.altDescription,
    required this.urls,
    required this.links,
    required this.likes,
    required this.likedByUser,
    required this.currentUserCollections,
    required this.sponsorship,
    required this.topicSubmissions,
    required this.user,
  });

  String id;
  DateTime createdAt;
  DateTime updatedAt;
  DateTime? promotedAt;
  int width;
  int height;
  String color;
  String blurHash;
  String description;
  String altDescription;
  Urls urls;
  WelcomeLinks links;
  int likes;
  bool likedByUser;
  List<dynamic> currentUserCollections;
  dynamic sponsorship;
  PurpleTopicSubmissions topicSubmissions;
  User user;

  factory ResultCoverPhoto.fromJson(Map<String, dynamic> json) => ResultCoverPhoto(
    id: json["id"],
    createdAt: DateTime.parse(json["created_at"]),
    updatedAt: DateTime.parse(json["updated_at"]),
    promotedAt: json["promoted_at"] == null ? null : DateTime.parse(json["promoted_at"]),
    width: json["width"],
    height: json["height"],
    color: json["color"],
    blurHash: json["blur_hash"],
    description: json["description"],
    altDescription: json["alt_description"],
    urls: Urls.fromJson(json["urls"]),
    links: WelcomeLinks.fromJson(json["links"]),
    likes: json["likes"],
    likedByUser: json["liked_by_user"],
    currentUserCollections: List<dynamic>.from(json["current_user_collections"].map((x) => x)),
    sponsorship: json["sponsorship"],
    topicSubmissions: PurpleTopicSubmissions.fromJson(json["topic_submissions"]),
    user: User.fromJson(json["user"]),
  );

}

class PurpleTopicSubmissions {
  PurpleTopicSubmissions({
    required this.wallpapers,
    required this.spirituality,
  });

  Spirituality? wallpapers;
  Spirituality? spirituality;

  factory PurpleTopicSubmissions.fromJson(Map<String, dynamic> json) => PurpleTopicSubmissions(
    wallpapers: json["wallpapers"] == null ? null : Spirituality.fromJson(json["wallpapers"]),
    spirituality: json["spirituality"] == null ? null : Spirituality.fromJson(json["spirituality"]),
  );

}

class Spirituality {
  Spirituality({
    required this.status,
    required this.approvedOn,
  });

  String status;
  DateTime approvedOn;

  factory Spirituality.fromJson(Map<String, dynamic> json) => Spirituality(
    status: json["status"],
    approvedOn: DateTime.parse(json["approved_on"]),
  );

  Map<String, dynamic> toJson() => {
    "status": status,
    "approved_on": approvedOn.toIso8601String(),
  };
}

class Urls {
  Urls({
    required this.raw,
    required this.full,
    required this.regular,
    required this.small,
    required this.thumb,
    required this.smallS3,
  });

  String raw;
  String full;
  String regular;
  String small;
  String thumb;
  String smallS3;

  factory Urls.fromJson(Map<String, dynamic> json) => Urls(
    raw: json["raw"],
    full: json["full"],
    regular: json["regular"],
    small: json["small"],
    thumb: json["thumb"],
    smallS3: json["small_s3"],
  );

  Map<String, dynamic> toJson() => {
    "raw": raw,
    "full": full,
    "regular": regular,
    "small": small,
    "thumb": thumb,
    "small_s3": smallS3,
  };
}

class User {
  User({
    required this.id,
    required this.updatedAt,
    required this.username,
    required this.name,
    required this.firstName,
    required this.lastName,
    required this.twitterUsername,
    required this.portfolioUrl,
    required this.bio,
    required this.location,
    required this.links,
    required this.profileImage,
    required this.instagramUsername,
    required this.totalCollections,
    required this.totalLikes,
    required this.totalPhotos,
    required this.acceptedTos,
    required this.forHire,
    required this.social,
  });

  String id;
  DateTime updatedAt;
  String username;
  String name;
  String firstName;
  String lastName;
  String twitterUsername;
  String portfolioUrl;
  String bio;
  String location;
  UserLinks links;
  ProfileImage profileImage;
  String instagramUsername;
  int totalCollections;
  int totalLikes;
  int totalPhotos;
  bool acceptedTos;
  bool forHire;
  Social social;

  factory User.fromJson(Map<String, dynamic> json) => User(
    id: json["id"],
    updatedAt: DateTime.parse(json["updated_at"]),
    username: json["username"],
    name: json["name"],
    firstName: json["first_name"],
    lastName: json["last_name"],
    twitterUsername: json["twitter_username"],
    portfolioUrl: json["portfolio_url"],
    bio: json["bio"],
    location: json["location"],
    links: UserLinks.fromJson(json["links"]),
    profileImage: ProfileImage.fromJson(json["profile_image"]),
    instagramUsername: json["instagram_username"],
    totalCollections: json["total_collections"],
    totalLikes: json["total_likes"],
    totalPhotos: json["total_photos"],
    acceptedTos: json["accepted_tos"],
    forHire: json["for_hire"],
    social: Social.fromJson(json["social"]),
  );

  Map<String, dynamic> toJson() => {
    "id": id,
    "updated_at": updatedAt.toIso8601String(),
    "username": username,
    "name": name,
    "first_name": firstName,
    "last_name": lastName,
    "twitter_username": twitterUsername,
    "portfolio_url": portfolioUrl,
    "bio": bio,
    "location": location,
    "links": links.toJson(),
    "profile_image": profileImage.toJson(),
    "instagram_username": instagramUsername,
    "total_collections": totalCollections,
    "total_likes": totalLikes,
    "total_photos": totalPhotos,
    "accepted_tos": acceptedTos,
    "for_hire": forHire,
    "social": social.toJson(),
  };
}

class UserLinks {
  UserLinks({
    required this.self,
    required this.html,
    required this.photos,
    required this.likes,
    required this.portfolio,
    required this.following,
    required this.followers,
  });

  String self;
  String html;
  String photos;
  String likes;
  String portfolio;
  String following;
  String followers;

  factory UserLinks.fromJson(Map<String, dynamic> json) => UserLinks(
    self: json["self"],
    html: json["html"],
    photos: json["photos"],
    likes: json["likes"],
    portfolio: json["portfolio"],
    following: json["following"],
    followers: json["followers"],
  );

  Map<String, dynamic> toJson() => {
    "self": self,
    "html": html,
    "photos": photos,
    "likes": likes,
    "portfolio": portfolio,
    "following": following,
    "followers": followers,
  };
}

class ProfileImage {
  ProfileImage({
    required this.small,
    required this.medium,
    required this.large,
  });

  String small;
  String medium;
  String large;

  factory ProfileImage.fromJson(Map<String, dynamic> json) => ProfileImage(
    small: json["small"],
    medium: json["medium"],
    large: json["large"],
  );

  Map<String, dynamic> toJson() => {
    "small": small,
    "medium": medium,
    "large": large,
  };
}

class Social {
  Social({
    required this.instagramUsername,
    required this.portfolioUrl,
    required this.twitterUsername,
    required this.paypalEmail,
  });

  String instagramUsername;
  String portfolioUrl;
  String twitterUsername;
  dynamic paypalEmail;

  factory Social.fromJson(Map<String, dynamic> json) => Social(
    instagramUsername: json["instagram_username"],
    portfolioUrl: json["portfolio_url"],
    twitterUsername: json["twitter_username"],
    paypalEmail: json["paypal_email"],
  );

  Map<String, dynamic> toJson() => {
    "instagram_username": instagramUsername,
    "portfolio_url": portfolioUrl,
    "twitter_username": twitterUsername,
    "paypal_email": paypalEmail,
  };
}

class ResultLinks {
  ResultLinks({
    required this.self,
    required this.html,
    required this.photos,
    required this.related,
  });

  String self;
  String html;
  String photos;
  String related;

  factory ResultLinks.fromJson(Map<String, dynamic> json) => ResultLinks(
    self: json["self"],
    html: json["html"],
    photos: json["photos"],
    related: json["related"],
  );

  Map<String, dynamic> toJson() => {
    "self": self,
    "html": html,
    "photos": photos,
    "related": related,
  };
}

class PreviewPhoto {
  PreviewPhoto({
    required this.id,
    required this.createdAt,
    required this.updatedAt,
    required this.blurHash,
    required this.urls,
  });

  String id;
  DateTime createdAt;
  DateTime updatedAt;
  String blurHash;
  Urls urls;

  factory PreviewPhoto.fromJson(Map<String, dynamic> json) => PreviewPhoto(
    id: json["id"],
    createdAt: DateTime.parse(json["created_at"]),
    updatedAt: DateTime.parse(json["updated_at"]),
    blurHash: json["blur_hash"],
    urls: Urls.fromJson(json["urls"]),
  );

  Map<String, dynamic> toJson() => {
    "id": id,
    "created_at": createdAt.toIso8601String(),
    "updated_at": updatedAt.toIso8601String(),
    "blur_hash": blurHash,
    "urls": urls.toJson(),
  };
}

class ResultTag {
  ResultTag({
    required this.type,
    required this.title,
    required this.source,
  });

  String type;
  String title;
  Source? source;

  factory ResultTag.fromJson(Map<String, dynamic> json) => ResultTag(
    type: json["type"],
    title: json["title"],
    source: json["source"] == null ? null : Source.fromJson(json["source"]),
  );


}

class Source {
  Source({
    required this.ancestry,
    required this.title,
    required this.subtitle,
    required this.description,
    required this.metaTitle,
    required this.metaDescription,
    required this.coverPhoto,
  });

  Ancestry ancestry;
  String title;
  String subtitle;
  String description;
  String metaTitle;
  String metaDescription;
  SourceCoverPhoto coverPhoto;

  factory Source.fromJson(Map<String, dynamic> json) => Source(
    ancestry: Ancestry.fromJson(json["ancestry"]),
    title: json["title"],
    subtitle: json["subtitle"],
    description: json["description"],
    metaTitle: json["meta_title"],
    metaDescription: json["meta_description"],
    coverPhoto: SourceCoverPhoto.fromJson(json["cover_photo"]),
  );


}

class Ancestry {
  Ancestry({
    required this.type,
    required this.category,
    required this.subcategory,
  });

  Type type;
  Type? category;
  Type? subcategory;

  factory Ancestry.fromJson(Map<String, dynamic> json) => Ancestry(
    type: Type.fromJson(json["type"]),
    category: json["category"] == null ? null : Type.fromJson(json["category"]),
    subcategory: json["subcategory"] == null ? null : Type.fromJson(json["subcategory"]),
  );


}

class Type {
  Type({
    required this.slug,
    required this.prettySlug,
  });

  String slug;
  String prettySlug;

  factory Type.fromJson(Map<String, dynamic> json) => Type(
    slug: json["slug"],
    prettySlug: json["pretty_slug"],
  );

  Map<String, dynamic> toJson() => {
    "slug": slug,
    "pretty_slug": prettySlug,
  };
}

class SourceCoverPhoto {
  SourceCoverPhoto({
    required this.id,
    required this.createdAt,
    required this.updatedAt,
    required this.promotedAt,
    required this.width,
    required this.height,
    required this.color,
    required this.blurHash,
    required this.description,
    required this.altDescription,
    required this.urls,
    required this.links,
    required this.likes,
    required this.likedByUser,
    required this.currentUserCollections,
    required this.sponsorship,
    required this.topicSubmissions,
    required this.premium,
    required this.user,
  });

  String id;
  DateTime createdAt;
  DateTime updatedAt;
  DateTime? promotedAt;
  int width;
  int height;
  String color;
  String blurHash;
  String description;
  String altDescription;
  Urls urls;
  WelcomeLinks links;
  int likes;
  bool likedByUser;
  List<dynamic> currentUserCollections;
  dynamic sponsorship;
  FluffyTopicSubmissions topicSubmissions;
  bool premium;
  User user;

  factory SourceCoverPhoto.fromJson(Map<String, dynamic> json) => SourceCoverPhoto(
    id: json["id"],
    createdAt: DateTime.parse(json["created_at"]),
    updatedAt: DateTime.parse(json["updated_at"]),
    promotedAt: json["promoted_at"] == null ? null : DateTime.parse(json["promoted_at"]),
    width: json["width"],
    height: json["height"],
    color: json["color"],
    blurHash: json["blur_hash"],
    description: json["description"],
    altDescription: json["alt_description"],
    urls: Urls.fromJson(json["urls"]),
    links: WelcomeLinks.fromJson(json["links"]),
    likes: json["likes"],
    likedByUser: json["liked_by_user"],
    currentUserCollections: List<dynamic>.from(json["current_user_collections"].map((x) => x)),
    sponsorship: json["sponsorship"],
    topicSubmissions: FluffyTopicSubmissions.fromJson(json["topic_submissions"]),
    premium: json["premium"],
    user: User.fromJson(json["user"]),
  );


}

class FluffyTopicSubmissions {
  FluffyTopicSubmissions({
    required this.nature,
    required this.wallpapers,
    required this.architectureInterior,
    required this.colorOfWater,
    required this.texturesPatterns,
  });

  Spirituality? nature;
  Spirituality? wallpapers;
  Spirituality? architectureInterior;
  Spirituality? colorOfWater;
  Spirituality? texturesPatterns;

  factory FluffyTopicSubmissions.fromJson(Map<String, dynamic> json) => FluffyTopicSubmissions(
    nature: json["nature"] == null ? null : Spirituality.fromJson(json["nature"]),
    wallpapers: json["wallpapers"] == null ? null : Spirituality.fromJson(json["wallpapers"]),
    architectureInterior: json["architecture-interior"] == null ? null : Spirituality.fromJson(json["architecture-interior"]),
    colorOfWater: json["color-of-water"] == null ? null : Spirituality.fromJson(json["color-of-water"]),
    texturesPatterns: json["textures-patterns"] == null ? null : Spirituality.fromJson(json["textures-patterns"]),
  );

}

class TagsPreviewElement {
  TagsPreviewElement({
    required this.type,
    required this.title,
  });

  String type;
  String title;

  factory TagsPreviewElement.fromJson(Map<String, dynamic> json) => TagsPreviewElement(
    type: json["type"],
    title: json["title"],
  );

  Map<String, dynamic> toJson() => {
    "type": type,
    "title": title,
  };
}

class WelcomeTopicSubmissions {
  WelcomeTopicSubmissions();

  factory WelcomeTopicSubmissions.fromJson(Map<String, dynamic> json) => WelcomeTopicSubmissions(
  );

  Map<String, dynamic> toJson() => {
  };
}
