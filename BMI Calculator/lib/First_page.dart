import 'package:bmi_calculator/Button.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'cards.dart';
import 'cardContent.dart';
import 'package:bmi_calculator/Button.dart';
import 'calculated.dart';
import 'brain.dart';

const bottomcontainerColour = Colors.lightGreenAccent;
const boxColour = Color(0xFF262740);
const activeColour = Color(0xFF262740);
const inactiveColour = Color(0xFF181928);
const inactiveIcon = Colors.black;
const activeIcon = Colors.white70;

enum Gender {
  male,
  female,
}

class InputPage extends StatefulWidget {
  @override
  _InputPageState createState() => _InputPageState();
}

class _InputPageState extends State<InputPage> {
  int height = 180;
  int weight = 30;
  int age = 18;
  Color maleBoxColour = inactiveColour;
  Color femaleBoxColour = inactiveColour;
  Color femaleIconColor = inactiveIcon;
  Color maleIconColor = inactiveIcon;

  void colourChange(int gender) {
    if (gender == 1) {
      if (maleBoxColour == inactiveColour) {
        maleBoxColour = activeColour;
        femaleBoxColour = inactiveColour;
        maleIconColor = activeIcon;
        femaleIconColor = inactiveIcon;
      } else {
        maleBoxColour = inactiveColour;
      }
    }
    if (gender == 2) {
      if (femaleBoxColour == inactiveColour) {
        femaleBoxColour = activeColour;
        maleBoxColour = inactiveColour;
        femaleIconColor = activeIcon;
        maleIconColor = inactiveIcon;
      } else {
        femaleBoxColour = inactiveColour;
      }
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Center(
            child: Text(
          'BMI Calculator',
          style: TextStyle(
            fontFamily: 'Caveat',
            fontSize: 40,
          ),
        )),
      ),
      body: Column(
        crossAxisAlignment: CrossAxisAlignment.stretch,
        children: [
          Expanded(
              child: Row(
            children: [
              Expanded(
                child: GestureDetector(
                  onTap: () {
                    setState(() {
                      colourChange(1);
                    });
                  },
                  child: Box(
                    colour: maleBoxColour,
                    cardChild: iconMaterial(
                      colour: maleIconColor,
                      icon: FontAwesomeIcons.mars,
                      gender: 'MALE',
                    ),
                  ),
                ),
              ),
              Expanded(
                child: GestureDetector(
                  onTap: () {
                    setState(() {
                      colourChange(2);
                    });
                  },
                  child: Box(
                    colour: femaleBoxColour,
                    cardChild: iconMaterial(
                      colour: femaleIconColor,
                      icon: FontAwesomeIcons.venus,
                      gender: 'FEMALE',
                    ),
                  ),
                ),
              )
            ],
          )),
          Expanded(
            child: Box(
                colour: boxColour,
                cardChild: Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    Text(
                      'HEIGHT',
                      style: TextStyle(color: Colors.grey, fontSize: 25.0),
                    ),
                    Container(
                      decoration: BoxDecoration(
                        borderRadius: BorderRadius.circular(12.0),
                      ),
                      margin: EdgeInsets.all(20.0),
                    ),
                    Row(
                      mainAxisAlignment: MainAxisAlignment.center,
                      crossAxisAlignment: CrossAxisAlignment.baseline,
                      textBaseline: TextBaseline.alphabetic,
                      children: [
                        Text(
                          height.toString(),
                          style: TextStyle(
                              color: Colors.grey,
                              fontSize: 50,
                              fontWeight: FontWeight.w900),
                        ),
                        Text(
                          'cm',
                          style: TextStyle(color: Colors.grey, fontSize: 25),
                        ),
                      ],
                    ),
                    Slider(
                      value: height.toDouble(),
                      activeColor: Colors.lightGreenAccent,
                      inactiveColor: Color(0xFF181928),
                      min: 120.0,
                      max: 250.0,
                      onChanged: (double newValue) {
                        setState(() {
                          height = newValue.round();
                        });
                      },
                    ),
                  ],
                )),
          ),
          Expanded(
              child: Row(
            children: [
              Expanded(
                child: Box(
                    colour: boxColour,
                    cardChild: Column(
                      crossAxisAlignment: CrossAxisAlignment.center,
                      children: [
                        Padding(
                          padding: const EdgeInsets.all(8.0),
                          child: Text(
                            'WEIGHT',
                            style: TextStyle(
                              fontSize: 25.0,
                              color: Colors.grey,
                            ),
                          ),
                        ),
                        Text(
                          weight.toString(),
                          style: TextStyle(
                              fontSize: 50.0,
                              color: Colors.grey,
                              fontWeight: FontWeight.w900),
                        ),
                        Row(
                          mainAxisAlignment: MainAxisAlignment.center,
                          children: [
                            RoundIconButton(
                              onpress: () {
                                setState(() {
                                  weight--;
                                });
                              },
                              icon: FontAwesomeIcons.minus,
                            ),
                            SizedBox(
                              width: 15.0,
                            ),
                            RoundIconButton(
                              onpress: () {
                                setState(() {
                                  weight++;
                                });
                              },
                              icon: FontAwesomeIcons.plus,
                            )
                          ],
                        )
                      ],
                    )),
              ),
              Expanded(
                child: Box(
                    colour: boxColour,
                    cardChild: Column(
                      crossAxisAlignment: CrossAxisAlignment.center,
                      children: [
                        Padding(
                          padding: const EdgeInsets.all(8.0),
                          child: Text(
                            'AGE',
                            style:
                                TextStyle(fontSize: 25.0, color: Colors.grey),
                          ),
                        ),
                        Text(
                          age.toString(),
                          style: TextStyle(
                              fontSize: 50.0,
                              color: Colors.grey,
                              fontWeight: FontWeight.w900),
                        ),
                        Row(
                          mainAxisAlignment: MainAxisAlignment.center,
                          children: [
                            RoundIconButton(
                              onpress: () {
                                setState(() {
                                  age--;
                                });
                              },
                              icon: FontAwesomeIcons.minus,
                            ),
                            SizedBox(
                              width: 15.0,
                            ),
                            RoundIconButton(
                                onpress: () {
                                  setState(() {
                                    age++;
                                  });
                                },
                                icon: FontAwesomeIcons.plus)
                          ],
                        )
                      ],
                    )),
              )
            ],
          )),
          GestureDetector(
            onTap: () {
              Brain b = Brain(height: height, weight: weight);
              Navigator.push(
                context,
                MaterialPageRoute(
                  builder: (context) => result(
                    BmiResult: b.Calulate(),
                    ResultText: b.ResultString(),
                    FeedbackText: b.FeedbackToUser(),
                  ),
                ),
              );
            },
            child: Container(
              alignment: Alignment.center,
              child: Text(
                'Calculate',
                style: TextStyle(fontWeight: FontWeight.w500, fontSize: 25.0),
              ),
              decoration: BoxDecoration(
                color: bottomcontainerColour,
                borderRadius: BorderRadius.circular(9.0),
              ),
              // color: bottomcontainerColour,
              margin: EdgeInsets.only(left: 50, top: 15, right: 50, bottom: 15),
              height: 50,
              width: double.infinity,
            ),
          ),
        ],
      ),
    );
  }
}
