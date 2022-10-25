class ScreenArgumentsAppointment{
  late final String patientName;
  late final String email;
  late final String phoneNo;
  late final String patientUid;
  late final String hospitalUid;
  late final String doctorName;
  late final String doctorPost;
  late final String doctorSpeciality;
  late final String doctorEducation;
  late final String date;
  late final String fromTime;
  late final String toTime;
  final String myId;
  final String patientAcceptListId;

  ScreenArgumentsAppointment(
      this.patientName, this.email, this.phoneNo,
      this.patientUid, this.hospitalUid, this.doctorName, this.doctorPost,
      this.doctorSpeciality, this.doctorEducation, this.date, this.fromTime,
      this.toTime, this.myId, this.patientAcceptListId);
}