# hiccup-pipeline

Automatically include CSS and JS files into your Hiccup based HTML page.

## Installation

```clojure
[hiccup-pipeline "0.1.0"]
```

## Usage

If your CSS files are in `resources/public/css` and your JS files are in `resources/public/js`:

```clojure
(defn index []
  (html
    [:head
      (include-all-css)
      (include-all-js)]
    [:body]))
```

If your CSS or JS files are in another directory:

```clojure
(defn index []
  (html
    [:head
      (include-all-css "path-to-css")
      (include-all-js "path-to-js")]
    [:body]))
```


## License

Copyright © 2012 Michael Drogalis

Distributed under the Eclipse Public License, the same as Clojure.
