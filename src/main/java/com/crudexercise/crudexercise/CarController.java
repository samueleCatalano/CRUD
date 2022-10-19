package com.crudexercise.crudexercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    CarRepository carRepository;

    @GetMapping("/get")
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public Car getSingleCar(Car car, @PathVariable Long id) {
        if (carRepository.existsById(id)) {
            car = carRepository.findById(id).get();
        }
        return car;

    }

    @PostMapping("/post")
    public Car createCars(@RequestBody Car car) {

        return carRepository.save(car);
    }

    @PutMapping("/updates/{id}")
    public Car updateCars(@RequestBody Car car, @PathVariable Long id) {
        if (carRepository.existsById(id)) {
            car.setId(id);
            car = carRepository.save(car);
        }
        return car;
    }


    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteCar(@PathVariable Long id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
            return HttpStatus.OK;
        } else {
            return HttpStatus.CONFLICT;
        }
    }

    @DeleteMapping("/deleteAll")
    public HttpStatus deleteAllCars() {
        carRepository.deleteAll();
        return HttpStatus.OK;
    }
}