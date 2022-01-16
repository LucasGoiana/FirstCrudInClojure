(ns crud.functions.login.functions
  (:use
    [crud.database.users.queries],
    [crud.helpers.helpers],
    [crud.functions.login.jwt]),
  (:require
    [clj-commons.digest :as digest]
    ))

(defn make-login [request]
  (let [nm (:json-params request)],
    (let [email (nm :email)]
      (let [password (digest/md5 (nm :password))]
        (let [userData (login email password)]
          (if (= (count userData)  2)
            (return 201  (make-json {:jwt (generate-signature (get-in userData  [:user/idUser]) (get-in userData  [:user/idProfile]))}))
            (return 401 (make-json {:msg "Usuário ou senha incorretos!"}))))))))

