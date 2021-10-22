import 'dart:math';

class Brain {
  Brain({required this.height, required this.weight});
  final int height;
  final int weight;
  double _bmi = 0;
  String Calulate() {
    _bmi = weight / pow(height / 100, 2);
    return _bmi.toStringAsFixed(1);
  }

  String ResultString() {
    if (_bmi >= 25) {
      return 'OVER-WEIGHT!';
    } else if (_bmi > 18.5) {
      return 'NORMAL';
    } else
      return 'UNDER-WEIGHT';
  }

  String FeedbackToUser() {
    if (_bmi >= 25) {
      return 'You have higher than Normal, Do some Exercise!';
    } else if (_bmi > 18.5) {
      return 'You have Normal BMI, GOOD JOB BUDDY!';
    } else
      return 'You have lower than Normal, Eat some food man!';
  }
}
