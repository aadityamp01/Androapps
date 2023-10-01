import 'dart:convert';

List<Welcome> welcomeFromJson(String str) => List<Welcome>.from(json.decode(str).map((x) => Welcome.fromJson(x)));

class Welcome {
  Welcome({
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
  DateTime? createdAt;
  DateTime? updatedAt;
  DateTime? promotedAt;
  int? width;
  int? height;
  String? color;
  String? blurHash;
  String? description;
  String? altDescription;
  Urls urls;
  WelcomeLinks? links;
  int? likes;
  bool? likedByUser;
  List<dynamic>? currentUserCollections;
  Sponsorship? sponsorship;
  TopicSubmissions? topicSubmissions;
  User? user;

  factory Welcome.fromJson(Map<String, dynamic> json) => Welcome(
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
    sponsorship: json["sponsorship"] == null ? null : Sponsorship.fromJson(json["sponsorship"]),
    topicSubmissions: TopicSubmissions.fromJson(json["topic_submissions"]),
    user: User.fromJson(json["user"]),
  );

}

class WelcomeLinks {
  WelcomeLinks({
    required this.self,
    required this.html,
    required this.download,
    required this.downloadLocation,
  });

  String? self;
  String? html;
  String? download;
  String? downloadLocation;

  factory WelcomeLinks.fromJson(Map<String, dynamic> json) => WelcomeLinks(
    self: json["self"],
    html: json["html"],
    download: json["download"],
    downloadLocation: json["download_location"],
  );
}

class Sponsorship {
  Sponsorship({
    required this.impressionUrls,
    required this.tagline,
    required this.taglineUrl,
    required this.sponsor,
  });

  List<String>? impressionUrls;
  String? tagline;
  String? taglineUrl;
  User? sponsor;

  factory Sponsorship.fromJson(Map<String, dynamic> json) => Sponsorship(
    impressionUrls: List<String>.from(json["impression_urls"].map((x) => x)),
    tagline: json["tagline"],
    taglineUrl: json["tagline_url"],
    sponsor: User.fromJson(json["sponsor"]),
  );

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

  String? id;
  DateTime? updatedAt;
  String? username;
  String? name;
  String? firstName;
  String? lastName;
  String? twitterUsername;
  String? portfolioUrl;
  String? bio;
  String? location;
  UserLinks? links;
  ProfileImage? profileImage;
  String? instagramUsername;
  int? totalCollections;
  int? totalLikes;
  int? totalPhotos;
  bool? acceptedTos;
  bool? forHire;
  Social? social;

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

  String? self;
  String? html;
  String? photos;
  String? likes;
  String? portfolio;
  String? following;
  String? followers;

  factory UserLinks.fromJson(Map<String, dynamic> json) => UserLinks(
    self: json["self"],
    html: json["html"],
    photos: json["photos"],
    likes: json["likes"],
    portfolio: json["portfolio"],
    following: json["following"],
    followers: json["followers"],
  );

}

class ProfileImage {
  ProfileImage({
    required this.small,
    required this.medium,
    required this.large,
  });

  String? small;
  String? medium;
  String? large;

  factory ProfileImage.fromJson(Map<String, dynamic> json) => ProfileImage(
    small: json["small"],
    medium: json["medium"],
    large: json["large"],
  );

}

class Social {
  Social({
    required this.instagramUsername,
    required this.portfolioUrl,
    required this.twitterUsername,
    required this.paypalEmail,
  });

  String? instagramUsername;
  String? portfolioUrl;
  String? twitterUsername;
  dynamic paypalEmail;

  factory Social.fromJson(Map<String, dynamic> json) => Social(
    instagramUsername: json["instagram_username"],
    portfolioUrl: json["portfolio_url"],
    twitterUsername: json["twitter_username"],
    paypalEmail: json["paypal_email"],
  );

}

class TopicSubmissions {
  TopicSubmissions({
    required this.architectureInterior,
    required this.film,
    required this.experimental,
    required this.people,
    required this.travel,
    required this.wallpapers,
    required this.streetPhotography,
    required this.health,
    required this.currentEvents,
    required this.spirituality,
    required this.artsCulture,
  });

  ArchitectureInterior? architectureInterior;
  ArchitectureInterior? film;
  ArchitectureInterior? experimental;
  ArchitectureInterior? people;
  ArchitectureInterior? travel;
  ArchitectureInterior? wallpapers;
  ArchitectureInterior? streetPhotography;
  ArchitectureInterior? health;
  ArchitectureInterior? currentEvents;
  ArchitectureInterior? spirituality;
  ArchitectureInterior? artsCulture;

  factory TopicSubmissions.fromJson(Map<String, dynamic> json) => TopicSubmissions(
    architectureInterior: json["architecture-interior"] == null ? null : ArchitectureInterior.fromJson(json["architecture-interior"]),
    film: json["film"] == null ? null : ArchitectureInterior.fromJson(json["film"]),
    experimental: json["experimental"] == null ? null : ArchitectureInterior.fromJson(json["experimental"]),
    people: json["people"] == null ? null : ArchitectureInterior.fromJson(json["people"]),
    travel: json["travel"] == null ? null : ArchitectureInterior.fromJson(json["travel"]),
    wallpapers: json["wallpapers"] == null ? null : ArchitectureInterior.fromJson(json["wallpapers"]),
    streetPhotography: json["street-photography"] == null ? null : ArchitectureInterior.fromJson(json["street-photography"]),
    health: json["health"] == null ? null : ArchitectureInterior.fromJson(json["health"]),
    currentEvents: json["current-events"] == null ? null : ArchitectureInterior.fromJson(json["current-events"]),
    spirituality: json["spirituality"] == null ? null : ArchitectureInterior.fromJson(json["spirituality"]),
    artsCulture: json["arts-culture"] == null ? null : ArchitectureInterior.fromJson(json["arts-culture"]),
  );

}

class ArchitectureInterior {
  ArchitectureInterior({
    required this.status,
  });

  Status? status;

  factory ArchitectureInterior.fromJson(Map<String, dynamic> json) => ArchitectureInterior(
    status: statusValues.map![json["status"]],
  );

}

enum Status { UNEVALUATED }

final statusValues = EnumValues({
  "unevaluated": Status.UNEVALUATED
});

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

}

class EnumValues<T> {
  Map<String, T>? map;
  late Map<T, String>? reverseMap;

  EnumValues(this.map);

  Map<T, String>? get reverse {
    reverseMap ??= map?.map((k, v) => MapEntry(v, k));
    return reverseMap;
  }
}
