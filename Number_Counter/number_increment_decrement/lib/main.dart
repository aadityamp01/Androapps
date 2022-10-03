import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      //ThemeData
      theme: ThemeData(
        scaffoldBackgroundColor: const Color(0xffFFFFF2),
        textButtonTheme: TextButtonThemeData(
          style: ButtonStyle(
            backgroundColor: MaterialStateProperty.all(
              const Color(0xFFDE6449),
            ),
            elevation: MaterialStateProperty.all(10),
            shadowColor: MaterialStateProperty.all(
              const Color(0x90DE6449),
            ),
          ),
        ),
      ),
      home: const CounterApp(),
    );
  }
}

class CounterApp extends StatefulWidget {
  const CounterApp({Key? key}) : super(key: key);

  @override
  State<CounterApp> createState() => _CounterAppState();
}

class _CounterAppState extends State<CounterApp> {
  int counter = 0;
//Increment Function
  void incrementCounter() {
    setState(() {
      counter++;
    });
  }

//Decrement Function
  void decrementCounter() {
    setState(() {
      counter--;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          Center(
            child: Expanded(
              child: Text(
                counter.toString(),
                //Text Styling
                style: const TextStyle(
                  fontSize: 200,
                  fontWeight: FontWeight.bold,
                  color: Color(0xff41D3BD),
                  shadows: <Shadow>[
                    Shadow(
                      offset: Offset(8, 8),
                      blurRadius: 3.0,
                      color: Color(0x9041D3BD),
                    ),
                  ],
                ),
              ),
            ),
          ),
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: [
              TextButton(
                onPressed: () {
                  //Calling Function
                  decrementCounter();
                },
                child: const Padding(
                  padding: EdgeInsets.all(8.0),
                  child: Text(
                    'Press this Button \nto\n Decrement Counter',
                    //Text Styling
                    style: TextStyle(color: Colors.white),
                    textAlign: TextAlign.center,
                  ),
                ),
              ),
              TextButton(
                onPressed: () {
                  //Calling Function
                  incrementCounter();
                },
                child: const Padding(
                  padding: EdgeInsets.all(8.0),
                  child: Text(
                    'Press this Button \nto\n Increment Counter',
                    //Text Styling
                    style: TextStyle(color: Colors.white),
                    textAlign: TextAlign.center,
                  ),
                ),
              ),
            ],
          )
        ],
      ),
    );
  }
}
