#define echoPin 2
#define trigPin 3
#define led 4
int speakerPin = 9;
int numTones = 4;
int tones[] = {440, 294, 440, 294};

//defines pins for ultrasonic sensor, led and speaker

void setup() {
  Serial.begin (9600);
  pinMode(trigPin, OUTPUT);
  pinMode(echoPin, INPUT);
  pinMode(led, OUTPUT);
  
}
//declares setup

void loop() {
  long duration, distance;
  digitalWrite(trigPin, LOW); //trig low
  delayMicroseconds(2);
  digitalWrite(trigPin, HIGH); //trig high
  delayMicroseconds(10); 
  digitalWrite(trigPin, LOW); //trig low
  duration = pulseIn(echoPin, HIGH); //echo high
  distance = (duration/2) / 50; //works out distance
  if (distance >= 50) {  // This is where the LED On/Off happens
  digitalWrite(led,LOW); //if more than 50 led is off
}
//this part of code sends out the ultrasonic waves and bounces back to sensor to read distance
  else {
   Serial.print(distance);
    digitalWrite(led,HIGH);
    for (int i = 0; i < numTones; i++)
  {
    tone(speakerPin, tones[i]);
    delay(100);
  }
  noTone(speakerPin);
  
  }
  
  
}
//if less than 50 turn led on and sound speaker
