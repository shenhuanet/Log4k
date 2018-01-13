# Log4k
[![jCenter](https://img.shields.io/badge/version-1.0.0-yellowgreen.svg) ](https://dl.bintray.com/shenhuanetos/maven/com/shenhua/libs/log4k/1.0.0/)
[![Build Status](https://img.shields.io/travis/rust-lang/rust/master.svg)](https://bintray.com/shenhuanetos/maven/log4k)
[![Hex.pm](https://img.shields.io/hexpm/l/plug.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)

Log output framework based on Chain of Responsibility model for developing Android using the kotlin language.

![logo](https://raw.githubusercontent.com/shenhuanet/Log4k/master/art/log4k.png)

## Usage
### Maven build settings
build.gradle
```gradle
dependencies {
  compile 'com.shenhua.libs:log4k:1.0.0'
}
```
or maven
```maven
<dependency>
  <groupId>com.shenhua.libs</groupId>
  <artifactId>log4k</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```
or lvy
```lvy
<dependency org='com.shenhua.libs' name='log4k' rev='1.0.0'>
  <artifact name='log4k' ext='pom' ></artifact>
</dependency>
```
### Code
```kotlin
LK.log(obj: Any?)

// other
LK.d(msg: String?)
LK.v(msg: String?)
LK.i(msg: String?)
LK.w(msg: String?)
LK.e(msg: String?)
```
### Expand
```kotlin
LK.addHandler(handler: BaseHandler)
```
### Sample
```kotlin
val string = "Hello world."
LK.log(string)
```
![](https://github.com/shenhuanet/Log4k/blob/master/art/001.png)

```kotlin
val json = "{\n" +
        "\t\"key1\": true,\n" +
        "\t\"key2\": 123,\n" +
        "\t\"key3\": {\n" +
        "\t\t\"a\": \"123132\",\n" +
        "\t\t\"b\": 1.23\n" +
        "\t},\n" +
        "\t\"key4\": [{\n" +
        "\t\t\"key1\": true,\n" +
        "\t\t\"key2\": 123\n" +
        "\t}, {\n" +
        "\t\t\"key1\": true,\n" +
        "\t\t\"key2\": 123\n" +
        "\t}]\n" +
        "}"
LK.log(json)
```
![](https://github.com/shenhuanet/Log4k/blob/master/art/002.png)

```kotlin
val ids = ArrayList<String>()
ids.add("123")
ids.add("456")
LK.log(ids)
```
![](https://github.com/shenhuanet/Log4k/blob/master/art/003.png)

```kotlin
val users = ArrayList<User>()
users.add(User("zhangsan", 12))
users.add(User("lisi", 14))
LK.log(users)
```
![](https://github.com/shenhuanet/Log4k/blob/master/art/004.png)

```kotlin
val map = HashMap<String, Any>()
map.put("abc", 123)
map.put("def", true)
LK.log(map)
```
![](https://github.com/shenhuanet/Log4k/blob/master/art/005.png)

```kotlin
val bundle = Bundle()
bundle.putInt("a", 10)
bundle.putSerializable("user", user)
LK.log(bundle)
```
![](https://github.com/shenhuanet/Log4k/blob/master/art/006.png)

```kotlin
val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"))
LK.log(intent)
```
![](https://github.com/shenhuanet/Log4k/blob/master/art/007.png)

```kotlin
val uri = Uri.parse("content://" + "com.shenhua.libs.log4k" + "/user")
LK.log(uri)
```
![](https://github.com/shenhuanet/Log4k/blob/master/art/008.png)

```kotlin
val string = "Hello world."
LK.d(string)
```
![](https://github.com/shenhuanet/Log4k/blob/master/art/009.png)

```kotlin
class ThrowableHandler : BaseHandler(), Formatter<Throwable> {
    override fun handle(obj: Any): Boolean {
        if (obj is Throwable) {
            Log.e(LK.TAG, String.format(Box.getBox(), format(obj)))
            return true
        }
        return false
    }
    override fun format(t: Throwable): String {
        val sw = StringWriter(256)
        val pw = PrintWriter(sw, false)
        t.printStackTrace(pw)
        pw.flush()
        var message = sw.toString()
        message = message.replace("\n".toRegex(), "\n║ ")
        return message
    }
}

LK.addHandler(ThrowableHandler())
LK.log(NullPointerException("This is a NullPointerException."))
```
![](https://github.com/shenhuanet/Log4k/blob/master/art/010.png)

## About Me
CSDN：http://blog.csdn.net/klxh2009<br>
JianShu：http://www.jianshu.com/u/12a81897d5bc

## License

    Copyright 2017 shenhuanet

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.