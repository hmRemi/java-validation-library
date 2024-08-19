# Java Validation Library

A powerful Java library for validating object fields using custom constraints. This library supports a variety of constraints for ensuring data integrity, including checks for numerical ranges, string lengths, null values, and more.

## Features

- **Custom Constraints**: Define and use custom validation rules to meet specific requirements.
- **Numerical Constraints**: Validate numerical values against minimum and maximum limits.
- **String Constraints**: Ensure string fields meet length requirements or match specific patterns.
- **Date Constraints**: Validate dates to ensure they fall within acceptable ranges (e.g., past or future dates).
- **Null Checks**: Ensure fields are not null when required.
- **Comprehensive Validation**: Apply multiple constraints to object fields for robust data validation.

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
  - [ConstraintProcessor](#constraintprocessor)
  - [ValidationEngine](#validationengine)
  - [Assertions](#assertions)
- [License](#license)
- [Contributing](#contributing)
- [Author](#author)


## Installation

1. Clone the repository:
   `git clone https://github.com/hmRemi/java-validation-library.git`

2. Navigate to the project directory:
   `cd java-config-library`

3. Build the project with Maven:
   `mvn clean install`

## Usage

### ConstraintProcessor

`ConstraintProcessor` is used to apply constraints to object fields and validate them.

#### Example

```java
import dev.revere.validation.ConstraintProcessor;
import dev.revere.validation.exceptions.ConstraintViolationException;

public class Example {
    public static void main(String[] args) {
        ConstraintProcessor processor = new ConstraintProcessor();
        DateUser user = new DateUser(LocalDate.now().plusDays(1));

        try {
            processor.applyConstraints(user);
            System.out.println("Validation passed.");
        } catch (ConstraintViolationException | IllegalAccessException e) {
            System.out.println("Validation failed: " + e.getMessage());
        }
    }
}
```

### ValidationEngine

`ValidationEngine` provides a convenient interface to validate instances using the ConstraintProcessor.

#### Example

```java
import dev.revere.validation.ValidationEngine;
import dev.revere.validation.exceptions.ConstraintViolationException;

public class Example {
    public static void main(String[] args) {
        ValidationEngine engine = new ValidationEngine();
        DateUser user = new DateUser(LocalDate.now().plusDays(1));

        try {
            engine.validate(user);
            System.out.println("Validation passed.");
        } catch (ConstraintViolationException | IllegalAccessException e) {
            System.out.println("Validation failed: " + e.getMessage());
        }
    }
}
```

### Assertions

Utility classes for making assertions in tests or validations.

#### Example

```java
import dev.revere.validation.assertions.Assertions;

public class Example {
    public static void main(String[] args) {
        Assertions.assertThat().isTrue(1 + 1 == 2, "Math is broken!");
        System.out.println("Assertion passed.");
    }
}
```

## License

For an open-source license that requires attribution to Revere Development, the [MIT License](https://opensource.org/licenses/MIT) is a suitable choice. Here is the license text with the necessary attribution:

```text
MIT License

Copyright (c) 2024 Revere Development

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

## Contributing

We welcome contributions from the community! If you'd like to contribute to the project, please follow these steps:

1. **Fork the Project:** Start by forking the project to your own GitHub account using the "Fork" button at the top right of this repository.
2. **Create a New Branch:** Create a new branch in your forked repository. This branch will be dedicated to your feature, enhancement, or bug fix.
3. **Make Changes:** Implement your desired changes, whether it's a new feature, improvement, or fixing a bug. Please ensure your code adheres to the project's coding standards.
4. **Commit Your Changes:** Commit your changes with clear and concise commit messages that describe the purpose of each change.
5. **Push to Your Fork:** Push your changes to your forked repository on GitHub.
6. **Create a Pull Request:** Once you've pushed your changes to your fork, go to the original repository and create a pull request. Provide a detailed description of your changes and why they are valuable.

## Author

The Java Validation Library is developed and maintained by Revere Development. For any inquiries or to get in touch with the team, you can reach us at:

- **Website**: [revere.dev](https://www.revere.dev)
- **Email**: [support@revere.dev](mailto:support@revere.dev)

Thank you for using and contributing to the Java Validation Library!
