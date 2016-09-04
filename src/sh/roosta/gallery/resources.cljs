(ns sh.roosta.gallery.resources)

(def items
  [
   {:id 1 :name "Baby" :file "baby.jpg" :category :painting :desc "Oil painting of some baby, attempting a diverse colorscheme"}
   {:id 2 :name "Baptism" :file "baptism.jpg" :category :painting :desc "Watercolor baptism scene"}
   {:id 3 :name "Cake" :file "cake.jpg" :category :design :desc "One of several design suggestions for a webpage header"}
   {:id 4 :name "Capucha" :file "capucha.jpg" :category :vector :desc "Inspired by anime ghost figures, early vector attempt"}
   {:id 5 :name "Charcoal Nomad" :file "charnom.jpg" :category :design :desc "Charcoal masked nomad figure"}
   {:id 6 :name "Cloak in Motion" :file "cloak.jpg" :category :photo :desc "One is a series of dark room photographs experimenting with various light exposure settings during development"}
   {:id 7 :name "Conch" :file "conch.gif" :category :game :desc "Enemy design for an unfinished untitled shmup game"}
   {:id 8 :name "Post cover" :file "cover.jpg" :category :painting :desc "Conseptual cover for a comic book that was never finished"}
   {:id 9 :name "Kanal Session Design" :file "cs.jpg" :category :design :desc "One of several design suggestions for a skating event. Not used but I still like it"}
   {:id 10 :name "Drainpipes" :file "drainpipes.jpg" :category :photo :desc "Dark room development"}
   {:id 11 :name "Dunes" :file "dunes.jpg" :category :painting :desc "Sand dunes abstracted"}
   {:id 12 :name "Masked Pencil Sketch" :file "edn.jpg" :category :drawing :desc "More pencil drawings of a gass mask wearing figure"}
   {:id 13 :name "Pixel Explosion" :file "expl.gif" :category :game :desc "Explosion from an unfinished shmup game"}
   {:id 14 :name "Floating Island" :file "flisland.png" :category :game :desc "Floating island for an unfinished shmup game"}
   {:id 15 :name "Development fluid experiment" :file "fluid.jpg" :category :photo :desc "Messing around with development fluid that turned out kinda neat"}
   {:id 16 :name "Goat Boys LP cover design" :file "gbcover.jpg" :category :design :desc "Cover design for Goat Boys debut album" :link "https://open.spotify.com/artist/2GadG4cFR4bNH6fyLFt8JK"}
   {:id 17 :name "Ghost Guy" :file "ghost.jpg" :category :vector :desc "Early vector attempt at a ghost like creature inspired by anime/games and the masks ghosts wear"}
   {:id 18 :name "Gray Brother" :file "graybrother.jpg" :category :drawing :desc "Pencil drawing of a gass masked wearing person"}
   {:id 19 :name "Hooded Moon" :file "hoodie.jpg" :category :design :desc "Cover suggestion for a band, that was never used"}
   {:id 20 :name "Hufda" :file "hufda.jpg" :category :design :desc "Cover suggestion for a band, it that was never used"}
   {:id 21 :name "Selv Portrait with background" :file "inkdanfull.jpg" :category :painting :desc "Selv portrait with a superimposed background"}
   {:id 22 :name "Selv Portrait" :file "inkdans.jpg" :category :painting :desc "Selv portrait using ink on plastic"}
   {:id 23 :name "Insectlike" :file "insectlike.jpg" :category :drawing :desc "One of several inksect inspired mask drawings"}
   {:id 24 :name "Julie series nr 1" :file "js1.jpg" :category :photo :desc "First in a photo series taken during medieval festival and developed in a darkroom"}
   {:id 26 :name "Julie series nr 3" :file "js3.jpg" :category :photo :desc "Third in a photo series taken during medieval festival and developed in a darkroom"}
   {:id 27 :name "Julie series nr 4" :file "js4.jpg" :category :photo :desc "Forth in a photo series taken during medieval festival and developed in a darkroom"}
   {:id 28 :name "Julie series nr 5" :file "js5.jpg" :category :photo :desc "Fifth in a photo series taken during medieval festival and developed in a darkroom"}
   {:id 29 :name "Julie series nr 6" :file "js6.jpg" :category :photo :desc "Sixth in a photo series taken during medieval festival and developed in a darkroom"}
   {:id 30 :name "Kodak Ice" :file "kodak.jpg" :category :photo :desc "Failed to develop this macro image of some ice but the result I still like"}
   {:id 31 :name "Desert Lady" :file "lady.jpg" :category :painting :desc "Unfinished painting trying to depict a desert-like dress style"}
   {:id 32 :name "Candle lamp" :file "lamp.jpg" :category :diy :desc "Lamp made for christmas using laser cutter, paint and patience. See link for vector blueprint" :link "https://roosta.sh/gallery/img/lamp_vector.svg"}
   {:id 33 :name "Monoxieman Idle" :file "monoxieman_idle.gif" :category :game :desc "Idle animation character sprite for unfinished game"}
   {:id 34 :name "Monoxieman Jump" :file "monoxieman_jump.gif" :category :game :desc "Jump animation character sprite for unfinished game"}
   {:id 35 :name "Monoxieman Running" :file "monoxieman_running.gif" :category :game :desc "Running animation character sprite for unfinished game"}
   {:id 36 :name "More ink!" :file "moreink.jpg" :category :drawing :desc "Random sketch drawn with a rough hand"}
   {:id 37 :name "More mask!" :file "moremask.jpg" :category :drawing :desc "Random sketch drawn with a rough hand"}
   {:id 38 :name "Nomad Angle" :file "nomad-ang.jpg" :category :painting :desc "Titular nomad drawn from an angle. Comic book concept"}
   {:id 39 :name "Nomad Equipped" :file "nomad-eq.jpg" :category :drawing :desc "Nomad with equipment, comic book concept"}
   {:id 40 :name "Ouroboros" :file "ouroboros.jpg" :category :drawing :desc "Unfinished sketch of ouroboros"}
   {:id 41 :name "Pattern Composition" :file "pattern_comp.jpg" :category :drawing :desc "One of many patterns inspired loosely on the art nouveau style"}
   {:id 42 :name "Pray Pencil" :file "pray_pencil.jpg" :category :drawing :desc "Sketch of two praying figures"}
   {:id 43 :name "House gardener logo suggestion" :file "precore.png" :category :vector :desc "Logo suggestion for a design/gardening company. Not used but I still like it"}
   {:id 44 :name "Runny ink portrait" :file "riportrait.jpg" :category :painting :desc "Self portrait with gratuitous use of ink"}
   {:id 45 :name "Salt" :file "salt.jpg" :category :painting :desc "Salt/sand dunes abstracted illustration"}
   {:id 46 :name "Sectered" :file "sectered.jpg" :category :painting :desc "Gass mask wearing kid in the series of insect inspired gass masks"}
   {:id 47 :name "Shmup Screenshot" :file "shmup.png" :category :game :desc "Composit unfinished shmup screenshot from an unfinished untitled game"}
   {:id 48 :name "Sea of sand" :file "sos.jpg" :category :painting :desc "Mountain surrounded by sand. Looks in hindsight more like water"}
   {:id 49 :name "Splotch" :file "splotch.jpg" :category :painting :desc "Watercolor and ink drawing of a gassmask wearing creature"}
   {:id 50 :name "Stones study" :file "stones.jpg" :category :painting :desc "Study of stone texture, watercolor"}
   {:id 51 :name "Thirst" :file "thirst.jpg" :category :painting :desc "Thirsty fellow drawn with ink on rice paper"}
   {:id 52 :name "Three men" :file "tmen.jpg" :category :painting :desc "Card playing fellows drawn with ink on rice paper"}
   {:id 53 :name "VLN Cover suggestion" :file "vln.jpg" :category :design :desc "Pixel art cover suggestion for a band album"}
   {:id 54 :name "Wind Cover" :file "wind.jpg" :category :design :desc "Cover for a 60s inspired jam band. Marker on plastic"}
   {:id 55 :name "Wrap" :file "wrap.jpg" :category :drawing :desc "Cloth study, pencil"}
   ])
