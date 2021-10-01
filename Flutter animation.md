## code for basic flutter animation.
```
import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget
{
Tween<double> _scaleTween = Tween<double>(begin: 1, end: 5);
//ColorTween(begin: Colors.blue, end: Colors.purple)
//SizeTween(begin: Size(0,0), end: Size(10,10))
//AlignmentTween(begin: Alignment.center, end: Alignment.topRight)


@override
Widget build(BuildContext context)
{
return MaterialApp(
title: 'Animation',
home: Scaffold(
body: Center(
child: TweenAnimationBuilder(


tween: _scaleTween,
duration: Duration(milliseconds: 600),
builder: (context, scale, child){
return Transform.scale(scale: scale, child: child)

},

child: Container(
width: 200,
height: 200,
color: Colors.lightBlue[200],
//child: Center(
//child: Text('Animation',
style: Theme.of(context).textTheme.headline6,
//),
),
),
),
),
}
}
```
