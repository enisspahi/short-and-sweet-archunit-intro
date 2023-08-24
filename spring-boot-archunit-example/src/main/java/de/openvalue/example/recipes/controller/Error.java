package de.openvalue.example.recipes.controller;

public record Error(ErrorCode code, String message) {

    public enum ErrorCode { UNEXPECTED, NOT_FOUND }
}

