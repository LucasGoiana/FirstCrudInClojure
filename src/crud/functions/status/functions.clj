(ns crud.functions.status.functions
  (:use
    [crud.database.status.queries],
    [crud.helpers.helpers],
    [crud.functions.login.jwt]),
  (:require
    [clojure.data.json :as json]))

(defn make-status [request]
  (let [jwt (:headers request)]
    (if (= (unsign-token (jwt "authorization"  ))true)
      (let [nm (:json-params request)],
        (let [name (nm :name)]
          (make name)
          {:status  200
           :headers headerModified
           :body    (make-json {:msg "Cadastrado com Sucesso!"})})
        {:status  400
         :headers headerModified
         :body    (make-json {:msg "Login incorreto, por favor tente novamente!"})}))))

(defn read-status [request]
  (let [jwt (:headers request)]
    (if (= (unsign-token (jwt "authorization"  ))true)
      (let [response (readAll request)]
        {:status  200
         :headers headerModified
         :body    (json/write-str response )})
      {:status  400
       :headers headerModified
       :body    (make-json {:msg "Login incorreto, por favor tente novamente!"})})))

(defn read-status-by-id [request]
  (let [jwt (:headers request)]
    (if (= (unsign-token (jwt "authorization"  ))true)
      (let [response  (readById (get-in request [:path-params :id]))]
        {:status  200
         :headers headerModified
         :body    (json/write-str response )})
      {:status  400
       :headers headerModified
       :body    (make-json {:msg "Login incorreto, por favor tente novamente!"})})))


