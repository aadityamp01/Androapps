import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:hospital_app/HospitalAuthentication/Hospital_Methods.dart';
import 'package:hospital_app/utils/routes.dart';
import 'package:hospital_app/widgets/add_doctor_list_page.dart';
import 'package:hospital_app/widgets/doctor_appointment.dart';
import 'package:hospital_app/widgets/doctor_appointment_accepted.dart';
import 'package:hospital_app/widgets/doctor_me.dart';
import 'package:hospital_app/widgets/doctor_appointment_requested.dart';

class DoctorHomePage extends StatefulWidget {
  @override
  State<DoctorHomePage> createState() => _DoctorHomePageState();
}

class _DoctorHomePageState extends State<DoctorHomePage> {
  // int selectIndex = 0;
  //
  // static List<Widget> _widgetOptions = <Widget> [
  //   DoctorAppointment(),
  //   AddDoctorListPage(),
  //   DoctorMe(),
  // ];
  //
  // void _onItemTapped(int index){
  //   setState(() {
  //     selectIndex = index;
  //   });
  // }

  @override
  Widget build(BuildContext context) {
    final size = MediaQuery.of(context).size;
    return Scaffold(
      appBar: AppBar(
        title: Padding(
          padding: EdgeInsets.only(left: size.width / 6),
          child: Center(
              child: Text(
            "Hospital App",
            style: TextStyle(color: Colors.white),
          )),
        ),
        actions: [
          Container(
            child: IconButton(
                onPressed: () {
                  hospitalLogOut(context);
                },
                icon: Icon(
                  Icons.logout,
                  color: Colors.white,
                )),
          )
        ],
        backgroundColor: Color(0xff8f94fb),
        elevation: 0,
        automaticallyImplyLeading: false,
      ),
      body: Stack(children: [
        Stack(children: [
          Container(
            height: size.height / 2.5,
            decoration: BoxDecoration(
              borderRadius: BorderRadius.circular(size.width / 7.8),
              color: Color(0xff8f94fb),
            ),
          ),
          Container(
            height: size.height / 3.5,
            decoration: BoxDecoration(
              color: Color(0xff8f94fb),
            ),
          ),
        ]),
        Padding(
          padding: const EdgeInsets.only(top: 90.0),
          child: GridView.count(crossAxisCount: 2, children: [
            Padding(
              padding: const EdgeInsets.all(8.0),
              child: InkWell(
                onTap: () {
                  Navigator.pushNamed(context, MyRoute.doctorAppointmentRoute);
                },
                child: Card(
                  elevation: 3.0,
                  color: Colors.white,
                  shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(30)),
                  child: Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: Column(children: [
                      CircleAvatar(
                          backgroundImage:
                              AssetImage("Assets/images/appointment.png"),
                          radius: 50),
                      SizedBox(
                        height: 10.0,
                      ),
                      Flexible(
                          child: Text(
                        "Appointment",
                        textAlign: TextAlign.center,
                        style: TextStyle(color: Colors.black),
                      ))
                    ]),
                  ),
                ),
              ),
            ),
            Padding(
              padding: const EdgeInsets.all(8.0),
              child: InkWell(
                onTap: () {
                  Navigator.pushNamed(context, MyRoute.addDoctorListRoute);
                },
                child: Card(
                  elevation: 3.0,
                  color: Colors.white,
                  shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(30)),
                  child: Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: Column(children: [
                      CircleAvatar(
                          backgroundImage:
                              AssetImage("Assets/images/doctor.png"),
                          radius: 50),
                      SizedBox(
                        height: 10.0,
                      ),
                      Flexible(
                          child: Text(
                        "Doctor List",
                        textAlign: TextAlign.center,
                        style: TextStyle(color: Colors.black),
                      ))
                    ]),
                  ),
                ),
              ),
            ),
            Padding(
              padding: const EdgeInsets.all(8.0),
              child: InkWell(
                onTap: () {
                  Navigator.pushNamed(context, MyRoute.doctorHistoryRoute);
                },
                child: Card(
                  elevation: 3.0,
                  color: Colors.white,
                  shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(30)),
                  child: Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: Column(children: [
                      CircleAvatar(
                          backgroundImage:
                              AssetImage("Assets/images/medicalReport.png"),
                          radius: 50),
                      SizedBox(
                        height: 10.0,
                      ),
                      Flexible(
                          child: Text(
                        "Reports",
                        textAlign: TextAlign.center,
                        style: TextStyle(color: Colors.black),
                      ))
                    ]),
                  ),
                ),
              ),
            ),
            Padding(
              padding: const EdgeInsets.all(8.0),
              child: InkWell(
                onTap: () {
                  Navigator.pushNamed(context, MyRoute.doctorMeRoute);
                },
                child: Card(
                  elevation: 3.0,
                  color: Colors.white,
                  shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(30)),
                  child: Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: Column(children: [
                      CircleAvatar(
                          backgroundImage:
                              AssetImage("Assets/images/profile.png"),
                          radius: 50),
                      SizedBox(
                        height: 10.0,
                      ),
                      Flexible(
                          child: Text(
                        "Profile",
                        textAlign: TextAlign.center,
                        style: TextStyle(color: Colors.black),
                      ))
                    ]),
                  ),
                ),
              ),
            ),
          ]),
        )
      ]),
    );
  }
}
