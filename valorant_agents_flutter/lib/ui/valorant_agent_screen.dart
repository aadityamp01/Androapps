import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

class ValorantAgentScreen extends StatefulWidget {
  const ValorantAgentScreen({super.key});

  @override
  State<ValorantAgentScreen> createState() => _ValorantAgentScreenState();
}

class _ValorantAgentScreenState extends State<ValorantAgentScreen> {
  Future<List<dynamic>> getValorantAgent() async {
    String url = 'https://valorant-api.com/v1/agents?isPlayableCharacter=true';
    var response = await http.get(Uri.parse(url), headers: {
      'Content-Type': 'application/json',
      'Accept': 'application/json',
    });
    return json.decode(response.body)['data'];
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        color: const Color(0xff01111d),
        child: FutureBuilder<List<dynamic>>(
          future: getValorantAgent(),
          builder: (context, snapshot) {
            if (snapshot.hasData) {
              return Padding(
                padding: const EdgeInsets.all(8.0),
                child: ListView.builder(
                  itemCount: snapshot.data?.length,
                  itemBuilder: (context, index) {
                    var title = snapshot.data![index]['displayName'];
                    var role = snapshot.data![index]['role']['displayName'];
                    var agentImg = snapshot.data![index]['displayIcon'];
                    return Container(
                      height: 100,
                      color: const Color(0xff01111d),
                      child: Row(
                        children: [
                          Container(
                            width: MediaQuery.of(context).size.width / 5,
                            margin: const EdgeInsets.symmetric(
                                horizontal: 12, vertical: 10),
                            child: CircleAvatar(
                              radius: 40,
                              backgroundImage: NetworkImage("$agentImg"),
                            ),
                          ),
                          Expanded(
                            child: Column(
                              mainAxisAlignment: MainAxisAlignment.center,
                              crossAxisAlignment: CrossAxisAlignment.center,
                              children: [
                                Expanded(
                                  child: Text("$title",
                                      style: const TextStyle(
                                          fontSize: 20, color: Colors.white),
                                      maxLines: 1,
                                      overflow: TextOverflow.ellipsis),
                                ),
                                Expanded(
                                  child: Text(
                                    "Role: $role",
                                    style: const TextStyle(
                                        fontSize: 16, color: Colors.white),
                                  ),
                                ),
                                const SizedBox(
                                  height: 20,
                                ),
                              ],
                            ),
                          )
                        ],
                      ),
                    );
                  },
                ),
              );
            }
            return const Center(child: CircularProgressIndicator());
          },
        ),
      ),
    );
  }
}
