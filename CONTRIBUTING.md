Contributing
------------

[MIT license]: ./LICENSE.md
[fork]: https://github.com/GourdErwa/java-design/fork
[pr]: https://github.com/GourdErwa/java-design/compare

Hi there! We're thrilled that you'd like to contribute to this project. Your help is essential for keeping it great.

This project adheres to the **[MIT license][MIT license].** By participating, you are expected to uphold this code.

Submitting a pull request
-------------------------

0. [Fork][] and clone the repository (see Build Instructions in the Environment)
0. Create a new branch: `git checkout -b ${my-branch-name}`
0. Make your change, add tests, and make sure the tests still pass
0. Push to your fork and [submit a pull request][pr]
0. Pat your self on the back and wait for your pull request to be reviewed and merged.

Here are a few things you can do that will increase the likelihood of your pull request being accepted:

- Follow the existing code's style [alibaba_checks.xml](./checkstyle/alibaba_checks.xml).
- Keep your change as focused as possible. If there are multiple changes you would like to make that are not dependent upon each other, consider submitting them as separate pull requests.
- Write a [good commit message](http://tbaggery.com/2008/04/19/a-note-about-git-commit-messages.html).

## Environment

 * Intellij IDEA
 	* use Intellij IDEA plugs（Preferences>Plugins>Browse repositories>${Plugin name}）
    	* [CheckStyle-IDEA](https://plugins.jetbrains.com/plugin/1065-checkstyle-idea) activeFile（Preferences>Other Settings>Checkstyle>Configuration File>active [alibaba_checks.xml](./checkstyle/alibaba_checks.xml)）
    	* [Alibaba Java Coding Guidelines](https://plugins.jetbrains.com/plugin/10046-alibaba-java-coding-guidelines)
    	* [FindBugs](https://plugins.jetbrains.com/plugin/3847-findbugs-idea)
    	* [SonarLint](https://plugins.jetbrains.com/plugin/7973-sonarlint)
 * Maven3
 * JDK8


## Resources

- [Contributing to Open Source on GitHub](https://guides.github.com/activities/contributing-to-open-source/)
- [Using Pull Requests](https://help.github.com/articles/using-pull-requests/)
- [GitHub Help](https://help.github.com)
