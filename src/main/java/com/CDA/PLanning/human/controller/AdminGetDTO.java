package com.CDA.PLanning.human.controller;

/**
 * The type Admin get dto.
 */
public record AdminGetDTO(Long id, Long personId,String name, String surname, String adresse, String email, Long phoneNumber) {
}
