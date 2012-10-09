# hiccup-pipeline

Instantly include CSS and JS files into your Hiccup based HTML page.

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

If your CSS or JS files are in another direction:

```clojure
(defn index []
  (html
    [:head
      (include-all-css "path-to-css")
      (include-all-js "path-to-js")]
    [:body]))
```


## License

Copyright © 2012 FIXME

Distributed under the Eclipse Public License, the same as Clojure.
