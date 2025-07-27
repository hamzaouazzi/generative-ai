package fr.ho.genai_demo.output;

public record CnieModel(
        String firstName,
        String lastName,
        String birthDate,
        String nationality,
        String gender,
        String address,
        String birthPlace,
        String number
) {
}
