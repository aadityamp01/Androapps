import 'package:firebase_core/firebase_core.dart';
import 'package:flutter/material.dart';
import 'package:hospital_app/Authentication/Authenticate.dart';
import 'package:hospital_app/HospitalAuthentication/hospital_login_screen.dart';
import 'package:hospital_app/pages/DoctorHomePage.dart';
import 'package:hospital_app/widgets/add_doctor_list_page.dart';
import 'package:hospital_app/pages/app_home_page.dart';
import 'package:hospital_app/pages/appointment_list.dart';
import 'package:hospital_app/pages/doctor_appointment_detail_page.dart';
import 'package:hospital_app/pages/doctor_history_page.dart';
import 'package:hospital_app/pages/doctor_login_page.dart';
import 'package:hospital_app/pages/doctor_profile_page.dart';
import 'package:hospital_app/pages/doctor_request_detail_page.dart';
import 'package:hospital_app/pages/home_page.dart';
import 'package:hospital_app/pages/hospital_detail_page.dart';
import 'package:hospital_app/Authentication/login_screen.dart';
import 'package:hospital_app/pages/not_report.dart';
import 'package:hospital_app/pages/profile_page.dart';
import 'package:hospital_app/pages/report_page.dart';
import 'package:hospital_app/utils/routes.dart';
import 'package:hospital_app/widgets/appointment_requested.dart';
import 'package:hospital_app/widgets/doctor_appointment.dart';
import 'package:hospital_app/widgets/doctor_me.dart';
import 'package:animated_theme_switcher/animated_theme_switcher.dart';

Future<void> main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp();
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  final Future<FirebaseApp> _initialization = Firebase.initializeApp();

  @override
  Widget build(BuildContext context) {
    return FutureBuilder(
      future: _initialization,
      builder: (context, snapshot) {
        // CHeck for Errors
        if (snapshot.hasError) {
          print("Something went Wrong");
        }
        // once Completed, show your application
        if (snapshot.connectionState == ConnectionState.done) {
          return ThemeProvider(
              initTheme: ThemeData.light(),
              child: Builder(
                  builder: (context) =>
                      MaterialApp(debugShowCheckedModeBanner: false,
                          //theme: ThemeProvider.Of(context),
                          routes: {
                            "/": (context) => Authenticate(),
                            MyRoute.appHomeRoute: (context) => AppHomePage(),
                            MyRoute.loginRoute: (context) => LoginScreen(),
                            MyRoute.hospitalLoginRoute: (context) =>
                                HospitalLoginScreen(),
                            MyRoute.homeRoute: (context) => HomePage(),
                            MyRoute.detailRoute: (context) =>
                                HospitalDetailPage(),
                            MyRoute.profileRoute: (context) => ProfilePage(),
                            MyRoute.doctorLoginRoute: (context) =>
                                DoctorLoginPage(),
                            MyRoute.doctorHomeRoute: (context) =>
                                DoctorHomePage(),
                            MyRoute.doctorMeRoute: (context) => DoctorMe(),
                            MyRoute.doctorAppointmentRoute: (context) =>
                                DoctorAppointment(),
                            MyRoute.doctorAppointmentDetailRoute: (context) =>
                                DoctorAppointmentDetailPage(),
                            MyRoute.doctorRequestDetailRoute: (context) =>
                                DoctorRequestDetailPage(),
                            MyRoute.doctorProfileRoute: (context) =>
                                DoctorProfilePage(),
                            MyRoute.appointmentListRoute: (context) =>
                                AppointmentListPage(),
                            MyRoute.addDoctorListRoute: (context) =>
                                AddDoctorListPage(),
                            MyRoute.appointmentRequestedRoute: (context) =>
                                AppointmentRequested(),
                            MyRoute.reportRoute: (context) => ReportPage(),
                            MyRoute.doctorHistoryRoute: (context) =>
                                DoctorHistoryPage(),
                            MyRoute.notReportRoute: (context) => NotReport()
                          })));
        }
        return CircularProgressIndicator();
      },
    );
  }
}
