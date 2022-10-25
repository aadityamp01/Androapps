class UserModel {
  String? uid;
  String? name;
  String? address;
  String? phone_no;
  String? email;
  String? photo;

  UserModel(
      {this.address,
      this.email,
      this.name,
      this.phone_no,
      this.uid,
      this.photo});
  //recieving  data from server
  factory UserModel.fromMap(map) {
    return UserModel(
        uid: map['uid'],
        email: map['email'],
        address: map['address'],
        name: map['name'],
        phone_no: map['phone_no'],
        photo: map['photo']);
  }
  // sending data to server
  Map<String, dynamic> toMap() {
    return {
      'uid': uid,
      'email': email,
      'address': address,
      'name': name,
      'phone_no': phone_no,
      'photo': photo,
    };
  }
}
