#include <iostream>
#include <vector>
#include <cmath>
#include <queue>
#include <unordered_map>
#include <iomanip>
#include <tuple>

using namespace std;
// this code is conducted by barsha saha
struct Edge {
    int target_id;
    double dx, dy;
};

pair<double, double> toCartesian(double distance, double angle) {
    double rad = angle * 3.14 / 180.0;
    double x = distance * cos(rad);
    double y = distance * sin(rad);
    return {x, y};
}

double calculateDistance(double x, double y) {
    return sqrt(x * x + y * y);
}

double findShortestPath(int start_id, int end_id, unordered_map<int, vector<Edge>>& graph) {
    priority_queue<tuple<double, int, double, double>, vector<tuple<double, int, double, double>>, greater<>> pq;
    pq.emplace(0, start_id, 0, 0);
    unordered_map<int, bool> visited;
    
    while (!pq.empty()) {
        auto [current_dist, device_id, x, y] = pq.top();
        pq.pop();

        if (visited[device_id]) continue;
        visited[device_id] = true;

        if (device_id == end_id) return round(current_dist * 100) / 100.0;

        for (auto& edge : graph[device_id]) {
            if (visited[edge.target_id]) continue;
            
            double new_x = x + edge.dx;
            double new_y = y + edge.dy;
            double dist = calculateDistance(new_x, new_y);

            pq.emplace(dist, edge.target_id, new_x, new_y);
        }
    }
    
    return -1;
}

int main() {
    int N;
    cin >> N;

    vector<pair<int, int>> devices(N);
    for (int i = 0; i < N; ++i) {
        int id, count;
        char colon;
        cin >> id >> colon >> count;
        devices[i] = {id, count};
    }

    unordered_map<int, vector<Edge>> graph;
    for (int i = 0; i < N; ++i) {
        int device_id;
        cin >> device_id;
        int count = devices[i].second;
        
        for (int j = 0; j < count; ++j) {
            int target_id, distance, angle;
            cin >> target_id >> distance >> angle;
            auto [dx, dy] = toCartesian(distance, angle);
            graph[device_id].push_back({target_id, dx, dy});
            graph[target_id].push_back({device_id, -dx, -dy});
        }
    }

    int start_id, end_id;
    cin >> start_id >> end_id;

    double result = findShortestPath(start_id, end_id, graph);
    cout << fixed << setprecision(2) << result << endl;

    return 0;
}