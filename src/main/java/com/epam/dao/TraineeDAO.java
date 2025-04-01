// package com.epam.dao;

// import java.util.Map;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.stereotype.Repository;

// import com.epam.model.Trainee;

// @Repository
// public class TraineeDAO {
//     private final Map<String, Trainee> storage;

//     @Autowired
//     public TraineeDAO(@Qualifier("traineeStorage") Map<String, Trainee> storage) {
//         this.storage = storage;
//     }

//     public void save(Trainee trainee) {
//         storage.put(trainee.getUsername(), trainee);
//     }

//     public Trainee findByUsername(String username) {
//         return storage.get(username);
//     }

//     public boolean exists(String username) {
//         return storage.containsKey(username);
//     }
 
//     public Map<String, Trainee> getStorage() {
//         return storage;
//     }

//     public void delete(String username) {
//         storage.remove(username);
//     }
// }